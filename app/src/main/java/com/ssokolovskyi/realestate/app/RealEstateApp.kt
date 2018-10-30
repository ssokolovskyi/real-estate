package com.ssokolovskyi.realestate.app

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.ssokolovskyi.realestate.dagger.AppComponent
import com.ssokolovskyi.realestate.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class RealEstateApp : DaggerApplication() {

  private lateinit var appComponent: AppComponent

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)
    appComponent = DaggerAppComponent
      .builder()
      .application(this)
      .build()
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return appComponent
  }

  override fun onCreate() {
    super.onCreate()
    observeNetworkingState()
  }

  private fun observeNetworkingState() {
    registerReceiver(appComponent.getNetworkStateReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
  }
}
