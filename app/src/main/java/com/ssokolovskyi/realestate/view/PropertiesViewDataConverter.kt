package com.ssokolovskyi.realestate.view

import android.content.res.Resources
import com.ssokolovskyi.realestate.R
import com.ssokolovskyi.realestate.network.http.Property
import com.ssokolovskyi.realestate.view.model.PropertiesData
import com.ssokolovskyi.realestate.view.model.PropertyItem
import java.util.*
import javax.inject.Inject

class PropertiesViewDataConverter
@Inject constructor(private val resources: Resources) {

  fun convertData(properties: PropertiesData): List<PropertyItem> {
    return properties
      .items
      .map {
        PropertyItem(
          id = it.advertisementId,
          title = it.title,
          price = resources.getString(R.string.price, it.price.toString(), it.currency),
          address = formatAddress(it),
          imageUrl = it.pictures.firstOrNull(),
          bookmarked = properties.bookmarked.contains(it.advertisementId)
        )
      }
  }

  private fun formatAddress(it: Property): String {
    val country = Locale(Locale.getDefault().language, it.country).displayCountry
    return listOf(country, it.city, it.zip, it.street)
      .asSequence()
      .filterNotNull()
      .joinToString()
  }
}
