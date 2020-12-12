package com.squareit.connectify.lib

import android.animation.ObjectAnimator
import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.squareit.connectify.R

fun TextView.onInternetConnected(context: Context) {
    this.setText(R.string.internet_is_connected)
    this.setBackgroundColor(ContextCompat.getColor(context, R.color.colorGreen))
    context.startTipAnim(true, this)
}

fun TextView.onInternetNotConnected(context: Context) {
    this.setText(R.string.no_internet_connection)
    this.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBlack))
    context.startTipAnim(false, this)
}

fun Context.startTipAnim(isConnected: Boolean, textView: TextView) {
    val hideTip = ObjectAnimator.ofFloat(textView, "translationY", 300f)
    val showTip = ObjectAnimator.ofFloat(textView, "translationY", 0f)

    if (!isConnected) {
        showTip.start()
    } else {
        hideTip.startDelay = 1500
        hideTip.start()
    }
}