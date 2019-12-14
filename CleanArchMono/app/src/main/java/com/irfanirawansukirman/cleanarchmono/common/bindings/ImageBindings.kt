package com.irfanirawansukirman.cleanarchmono.common.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.irfanirawansukirman.cleanarchmono.common.ext.load

object ImageBindings {
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl)
    }
}