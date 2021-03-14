package com.irfanirawansukirman.core.ui.imageview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.irfanirawansukirman.core.databinding.ImageProgressBinding
import com.irfanirawansukirman.core.util.extension.load
import com.irfanirawansukirman.core.util.extension.loadCircle

class ImageProgress : FrameLayout {

    private lateinit var viewBinding: ImageProgressBinding

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun init() {
        viewBinding = ImageProgressBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun loadImageUrl(
        url: String,
        error: Int = 0,
        placeholder: Int = 0,
        isCircleCrop: Boolean = false
    ) {
        viewBinding.ivProgress.apply {
            if (isCircleCrop) {
                loadCircle(url, viewBinding.progress, error, placeholder)
            } else {
                load(url, viewBinding.progress, error, placeholder)
            }
        }
    }
}