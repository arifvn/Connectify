package com.squareit.connectify.lib

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import javax.inject.Inject

class Connectify @Inject constructor(context: Context) : LiveData<Boolean>() {

    private val cm: ConnectivityManager =
        context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val networkBuilder: NetworkRequest.Builder = NetworkRequest.Builder()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            postValue(true)
        }

        override fun onLost(network: Network) {
            postValue(false)
        }
    }

    init {
        postValue(false)
    }

    private fun startNetworkCallback() =
        cm.registerNetworkCallback(networkBuilder.build(), networkCallback)

    private fun stopNetworkCallback() {
        try {
            cm.unregisterNetworkCallback(networkCallback)
        } catch (e: IllegalAccessException) {
            println(e)
        }
    }

    override fun onActive() = startNetworkCallback()

    override fun onInactive() = stopNetworkCallback()
}