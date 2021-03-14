package com.irfanirawansukirman.core.util.extension


import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.irfanirawansukirman.core.R
import com.irfanirawansukirman.core.util.GlideApp

fun ImageView.load(
    @DrawableRes path: Int?,
    progress: ProgressBar? = null,
    @ColorRes error: Int = R.color.uiGrayMedium,
    @ColorRes placeholder: Int = R.color.uiGrayMedium
) {
    GlideApp.with(this)
        .load(path)
        .error(error)
        .placeholder(placeholder)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progress?.hide()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progress?.hide()
                return false
            }
        })
        .into(this)
}

fun ImageView.load(
    path: String?,
    progress: ProgressBar? = null,
    @ColorRes error: Int = R.color.uiGrayMedium,
    @ColorRes placeholder: Int = R.color.uiGrayMedium
) {
    GlideApp.with(this)
        .load(path)
        .error(error)
        .placeholder(placeholder)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progress?.hide()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progress?.hide()
                return false
            }
        })
        .into(this)
}

fun ImageView.loadCircle(
    path: String?,
    progress: ProgressBar? = null,
    @ColorRes error: Int = R.color.uiGrayMedium,
    @ColorRes placeholder: Int = R.color.uiGrayMedium
) {
    GlideApp.with(this)
        .load(path)
        .error(error)
        .placeholder(placeholder)
        .circleCrop()
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progress?.hide()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progress?.hide()
                return false
            }
        })
        .into(this)
}