package com.squareit.connectify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareit.connectify.databinding.ActivityMainBinding
import com.squareit.connectify.lib.Connectify
import com.squareit.connectify.lib.onInternetConnected
import com.squareit.connectify.lib.onInternetNotConnected
import com.squareit.viewbinder.viewBinder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinder()

    @Inject
    lateinit var connectify: Connectify

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectify.observe(this) { isConnected ->
            binding.apply {
                if (isConnected) tvConnection.onInternetConnected(this@MainActivity)
                else tvConnection.onInternetNotConnected(this@MainActivity)
            }
        }
    }
}