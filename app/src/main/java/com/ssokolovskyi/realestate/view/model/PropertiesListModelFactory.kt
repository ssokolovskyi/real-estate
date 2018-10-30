package com.ssokolovskyi.realestate.view.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider.NewInstanceFactory
import com.ssokolovskyi.realestate.network.NetworkAvailabilityProvider
import com.ssokolovskyi.realestate.view.PropertiesViewDataConverter
import javax.inject.Inject

class PropertiesListModelFactory
@Inject constructor(
    private val networkAvailabilityProvider: NetworkAvailabilityProvider,
    private val viewDataConverter: PropertiesViewDataConverter,
    private val propertiesDataModel: PropertiesDataModel
) : NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PropertiesListViewModel(
            networkAvailabilityProvider,
            viewDataConverter,
            propertiesDataModel
        ) as T
    }
}
