package com.ssokolovskyi.realestate.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStateReceiver
@Inject constructor() : BroadcastReceiver(), NetworkStateChanges {

  private val subject = PublishSubject.create<Unit>()

  override fun onReceive(context: Context?, intent: Intent?) {
    subject.onNext(Unit)
  }

  override fun networkChanges(): Observable<Unit> {
    return subject
  }
}

interface NetworkStateChanges {
  fun networkChanges(): Observable<Unit>
}
