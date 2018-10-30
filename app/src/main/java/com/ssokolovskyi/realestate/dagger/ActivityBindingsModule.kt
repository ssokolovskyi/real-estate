package com.ssokolovskyi.realestate.dagger

import com.ssokolovskyi.realestate.view.PropertiesListActivity
import com.ssokolovskyi.realestate.view.PropertiesListActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {
  @ContributesAndroidInjector(modules = [PropertiesListActivityModule::class])
  abstract fun propertiesListActivity(): PropertiesListActivity
}
