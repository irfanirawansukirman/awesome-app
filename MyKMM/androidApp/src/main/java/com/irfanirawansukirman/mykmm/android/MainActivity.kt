package com.irfanirawansukirman.mykmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.irfanirawansukirman.mykmm.Greeting
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this).get(MovieViewModel::class.java)
    }

    private lateinit var progress: ProgressBar
    private lateinit var ivPoster: ImageView
    private lateinit var tvTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progress = findViewById(R.id.progress)
        ivPoster = findViewById(R.id.iv_poster)
        tvTitle = findViewById(R.id.tv_title)

        viewModel.movie.observe(this, {
            when (it) {
                is State.Loading -> progress.visibility = View.VISIBLE
                is State.Success -> {
                    progress.visibility = View.GONE

                    Glide.with(this)
                        .load("https://image.tmdb.org/t/p/w500/${it.data[4].posterPath}")
                        .into(ivPoster)
                    tvTitle.text = it.data[4].originalTitle
                }
                is State.Error -> progress.visibility = View.GONE
            }
        })

        viewModel.getAllMovie()
    }
}
