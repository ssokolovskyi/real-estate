package com.ssokolovskyi.realestate.network.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtils {

  fun getRetrofit(url: String): Retrofit {
    return Retrofit.Builder()
      .baseUrl(url)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .client(getOkHttpClient())
      .build()
  }

  private fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .readTimeout(1, TimeUnit.MINUTES)
      .build()
  }
}
