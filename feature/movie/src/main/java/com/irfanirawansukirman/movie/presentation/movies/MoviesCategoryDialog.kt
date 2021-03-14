package com.irfanirawansukirman.movie.presentation.movies

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.irfanirawansukirman.core.util.extension.putArgs
import com.irfanirawansukirman.core.util.viewmodel.ViewModelFactory
import com.irfanirawansukirman.movie.R
import com.irfanirawansukirman.movie.databinding.MoviesCategoryDialogBinding
import com.irfanirawansukirman.movie.di.MovieComponentProvider
import com.irfanirawansukirman.movie.presentation.movies.MoviesCategoryState.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MoviesCategoryDialog : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by activityViewModels<MoviesVM> { viewModelFactory }

    private var viewBinding: MoviesCategoryDialogBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjector()
        super.onCreate(savedInstanceState)
    }

    private fun initInjector() {
        ((requireActivity() as MoviesActivity).application as MovieComponentProvider)
            .getMovieComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (viewBinding == null) {
            viewBinding = MoviesCategoryDialogBinding.inflate(inflater, container, false)
        }

        return viewBinding?.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog: BottomSheetDialog =
            super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            val bottomSheet =
                bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return bottomSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewListener()
    }

    private fun initViewListener() {
        viewBinding?.apply {
            tvPopular.setOnClickListener { dismiss(); setupCategory(Popular) }
            tvUpcoming.setOnClickListener { dismiss(); setupCategory(Upcoming) }
            tvTopRated.setOnClickListener { dismiss(); setupCategory(TopRated) }
            tvNowPlaying.setOnClickListener { dismiss(); setupCategory(NowPlaying) }
        }
    }

    private fun setupCategory(state: MoviesCategoryState) {
        viewModel.setupCategory(state)
    }

    companion object {
        fun newInstance() = MoviesCategoryDialog().putArgs { }
    }
}