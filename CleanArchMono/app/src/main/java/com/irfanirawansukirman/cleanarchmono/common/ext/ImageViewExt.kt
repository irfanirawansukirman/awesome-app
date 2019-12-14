package com.irfanirawansukirman.cleanarchmono.common.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.irfanirawansukirman.cleanarchmono.R

fun ImageView.load(path: String) {
    val baseImage = "https://image.tmdb.org/t/p/w500/"
    Glide.with(this)
        .load(baseImage + path.replace("\\", ""))
        .placeholder(R.drawable.cv_progress)
        .error(R.color.colorAccent)
        .into(this)
}

fun ImageView.loadWithCircleTransform(path: String) {
    val baseImage = "https://image.tmdb.org/t/p/w500/"
    Glide.with(this)
        .load(baseImage + path)
        .placeholder(R.drawable.cv_progress)
        .error(R.color.colorAccent)
        .circleCrop()
        .into(this)
}