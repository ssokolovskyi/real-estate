package com.ssokolovskyi.realestate.network

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Observable
import javax.inject.Inject

class NetworkAvailabilityProvider
@Inject constructor(
  private val context: Context,
  private val networkStateState: NetworkStateChanges
) {

  fun hasConnection(): Observable<Boolean> = networkStateState
    .networkChanges()
    .map { isNetworkAvailable() }
    .startWith(isNetworkAvailable())
    .distinctUntilChanged()

  private fun isNetworkAvailable() = context
    .getSystemService(Context.CONNECTIVITY_SERVICE)
    .let { it as ConnectivityManager }
    .activeNetworkInfo
    ?.isConnected ?: false
}
