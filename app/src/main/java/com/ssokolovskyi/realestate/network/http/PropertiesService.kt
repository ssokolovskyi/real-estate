package com.ssokolovskyi.realestate.network.http

import io.reactivex.Single
import retrofit2.http.GET

interface PropertiesService {
  companion object {
    private const val SERVICE_ENDPOINT = " http://private-492e5-homegate1.apiary-mock.com/"

    fun getService(): PropertiesService {
      return RetrofitUtils
        .getRetrofit(SERVICE_ENDPOINT)
        .create(PropertiesService::class.java)
    }
  }

  @GET("properties")
  fun getProperties(): Single<PropertiesResponse>
}

data class PropertiesResponse(val items: List<Property>)
data class Property(
  val advertisementId: Long,
  val title: String,
  val price: Double,
  val currency: String,
  val pictures: List<String>,
  val street: String?,
  val zip: String?,
  val city: String?,
  val country: String?
)
