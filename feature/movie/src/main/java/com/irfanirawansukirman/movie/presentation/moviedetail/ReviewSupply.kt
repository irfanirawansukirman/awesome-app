package com.irfanirawansukirman.movie.presentation.moviedetail

import androidx.recyclerview.widget.RecyclerView
import com.irfanirawansukirman.core.util.GlideApp
import com.irfanirawansukirman.core.util.extension.orDefault
import com.irfanirawansukirman.movie.BuildConfig
import com.irfanirawansukirman.movie.R
import com.irfanirawansukirman.movie.data.mapper.ReviewsUI
import com.irfanirawansukirman.movie.databinding.ReviewItemBinding

class ReviewViewHolder(private val viewBinding: ReviewItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bindItem(review: ReviewsUI, onReviewSelected: (String?) -> Unit) {
        viewBinding.apply {
            GlideApp.with(viewBinding.root.context)
                .load("${BuildConfig.MOVIE_POSTER_BASE_URL}${review.avatar}")
                .error(R.color.uiGrayMedium)
                .into(viewBinding.ivAvatar)
            tvName.text = review.name
            tvContent.text = review.content
            rbReview.rating = review.rating?.toFloat().orDefault(0f)

            root.setOnClickListener { onReviewSelected(review.url) }
        }
    }
}