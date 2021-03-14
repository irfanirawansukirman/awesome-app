package com.irfanirawansukirman.core.util.extension

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun <T> T?.orDefault(default: T): T {
    return this ?: default
}

fun getResColor(context: Context, @ColorRes color: Int): Int =
    ContextCompat.getColor(context, color)

fun getResDrawable(context: Context, @DrawableRes drawable: Int): Drawable? =
    ContextCompat.getDrawable(context, drawable)
