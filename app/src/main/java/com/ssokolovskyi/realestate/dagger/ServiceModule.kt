package com.ssokolovskyi.realestate.dagger

import com.ssokolovskyi.realestate.network.http.PropertiesService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceModule {
  @Singleton
  @Provides
  fun providePropertiesService(): PropertiesService {
    return PropertiesService.getService()
  }
}
