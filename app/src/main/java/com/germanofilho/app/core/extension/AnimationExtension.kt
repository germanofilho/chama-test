package com.germanofilho.app.core.extension

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

fun View.fadeAnimation(){
    this.alpha = 0.3f
    this.animate().setDuration(400)
        .setInterpolator(AccelerateDecelerateInterpolator())
        .alpha(1f)
}