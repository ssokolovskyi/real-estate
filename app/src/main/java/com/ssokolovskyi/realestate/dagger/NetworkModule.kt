package com.ssokolovskyi.realestate.dagger

import com.ssokolovskyi.realestate.network.NetworkStateChanges
import com.ssokolovskyi.realestate.network.NetworkStateReceiver
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
  @Provides
  fun getNetworkChanges(networkStateReceiver: NetworkStateReceiver): NetworkStateChanges {
    return networkStateReceiver
  }
}
