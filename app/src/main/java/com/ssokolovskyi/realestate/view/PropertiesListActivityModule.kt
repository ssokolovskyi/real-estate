package com.ssokolovskyi.realestate.view

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides

@Module
class PropertiesListActivityModule {
  @Provides
  fun provideContext(activity: PropertiesListActivity): Context = activity

  @Provides
  fun provideResources(activity: PropertiesListActivity): Resources = activity.resources
}
