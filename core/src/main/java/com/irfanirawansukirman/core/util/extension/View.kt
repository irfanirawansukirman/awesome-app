package com.irfanirawansukirman.core.util.extension

import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes

fun View.show(@AnimRes animation: Int? = null) {
    visibility = View.VISIBLE
    if (animation != null) loadAnimation(animation)
}

fun View.hide(@AnimRes animation: Int? = null) {
    if (animation != null) loadAnimation(animation)
    visibility = View.GONE
}

fun View.loadAnimation(@AnimRes anim: Int) {
    startAnimation(AnimationUtils.loadAnimation(context, anim))
}