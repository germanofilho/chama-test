package com.germanofilho.app.core.extension

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

fun View.fadeAnimation(duration: Long){
    this.alpha = 0.3f
    this.animate().setDuration(duration)
        .setInterpolator(AccelerateDecelerateInterpolator())
        .alpha(1f)
}