package ru.mggtk.booknook.check

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NetworkChangeReceiver(private val onNetworkChangedListener: OnNetworkChangedListener) :
    BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (InternetCheckUtil.isInternetAvailable(context)) {
            onNetworkChangedListener.onNetworkAvailable()
        } else {
            onNetworkChangedListener.onNetworkUnavailable()
        }
    }

    interface OnNetworkChangedListener {
        fun onNetworkAvailable()
        fun onNetworkUnavailable()
    }
}
