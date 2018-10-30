package com.ssokolovskyi.realestate.dagger

import com.ssokolovskyi.realestate.app.RealEstateApp
import com.ssokolovskyi.realestate.network.NetworkStateReceiver
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    ActivityBindingsModule::class,
    AndroidSupportInjectionModule::class,
    ServiceModule::class,
    DatabaseModule::class,
    NetworkModule::class
  ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: RealEstateApp): Builder

    fun build(): AppComponent
  }

  fun getNetworkStateReceiver(): NetworkStateReceiver
}
