package com.ssokolovskyi.realestate.view.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ssokolovskyi.realestate.network.NetworkAvailabilityProvider
import com.ssokolovskyi.realestate.view.PropertiesViewDataConverter
import com.ssokolovskyi.realestate.view.ViewAction
import com.ssokolovskyi.realestate.view.ViewAction.Bookmark
import com.ssokolovskyi.realestate.view.model.ViewData.ListData
import com.ssokolovskyi.realestate.view.model.ViewData.NoNetwork
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PropertiesListViewModel(
  networkAvailabilityProvider: NetworkAvailabilityProvider,
  viewDataConverter: PropertiesViewDataConverter,
  private val propertiesDataModel: PropertiesDataModel
) : ViewModel() {

  private var viewData = MutableLiveData<ViewData>()
  private val compositeDisposable = CompositeDisposable()

  init {
    compositeDisposable.add(
      networkAvailabilityProvider
        .hasConnection()
        .observeOn(Schedulers.io())
        .takeUntil { it }
        .toFlowable(BackpressureStrategy.BUFFER)
        .switchMap { hasConnection ->
          if (hasConnection) {
            propertiesDataModel
              .getProperties()
              .map { ListData(viewDataConverter.convertData(it)) }
          } else {
            Flowable.just(NoNetwork)
          }
        }
        .distinctUntilChanged()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { viewData.value = it }
    )
  }

  fun getViewData(): LiveData<ViewData> {
    return viewData
  }

  fun onViewAction(viewAction: ViewAction) {
    when (viewAction) {
      is Bookmark -> {
        compositeDisposable.add(
          propertiesDataModel
            .bookmarkProperty(viewAction.checked, viewAction.propertyId)
            .subscribe()
        )
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.dispose()
  }
}

sealed class ViewData {
  data class ListData(val items: List<PropertyItem>) : ViewData()
  object NoNetwork : ViewData()
}

data class PropertyItem(
  val id: Long,
  val title: String,
  val address: String,
  val price: String,
  val imageUrl: String?,
  val bookmarked: Boolean
)
