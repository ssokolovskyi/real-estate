package com.ssokolovskyi.realestate.view.model

import com.ssokolovskyi.realestate.database.BookmarkModel
import com.ssokolovskyi.realestate.database.BookmarksDao
import com.ssokolovskyi.realestate.network.http.PropertiesService
import com.ssokolovskyi.realestate.network.http.Property
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PropertiesDataModel
@Inject constructor(
  private val propertiesService: PropertiesService,
  private val bookmarksDao: BookmarksDao
) {

  fun getProperties(): Flowable<PropertiesData> {
    return Flowable
      .combineLatest<List<Long>, List<Property>, PropertiesData>(
        bookmarksDao.getAll().map { bookmarks -> bookmarks.map { it.id } },
        propertiesService.getProperties().map { it.items }.toFlowable(),
        BiFunction { bookmarks, properties ->
          PropertiesData(properties, bookmarks)
        }
      )
  }

  fun bookmarkProperty(checked: Boolean, id: Long): Completable {
    return Completable
      .fromAction {
        if (checked) bookmarksDao.insert(BookmarkModel(id = id))
        else bookmarksDao.delete(id)
      }
      .subscribeOn(Schedulers.io())
  }
}

data class PropertiesData(val items: List<Property>, val bookmarked: List<Long>)
