package com.irfanirawansukirman.cleanarchmono.ui.main.view

import com.irfanirawansukirman.cleanarchmono.R
import com.irfanirawansukirman.cleanarchmono.common.ext.*
import com.irfanirawansukirman.cleanarchmono.ui.base.*
import com.irfanirawansukirman.cleanarchmono.ui.main.presentation.MainVM
import com.irfanirawansukirman.data.network.model.MoviesResult
import com.irfanirawansukirman.domain.model.response.MovieInfo
import com.irfanirawansukirman.lib_recyclerviewgenericadapter.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), OnRecyclerItemClickListener {

    private val viewModel: MainVM by viewModel()

    private lateinit var mainAdapter: MainAdapter

    override fun bindLayoutId() = R.layout.main_activity

    override fun setupViewListener() {

    }

    override fun onLoadObserver() {
        viewModel.uiState.subscribe(this, ::movieUiState)
    }

    override fun setupComponents() {
        mainAdapter = MainAdapter(this, this)
    }

    private fun movieUiState(uiState: UiState<MovieInfo>) {
        when (uiState) {
            is Loading -> {
                progress.visible()
                btnMain.gone()
            }
            is Success -> {
                val data = uiState.data.movieList?.asListOfType<MoviesResult>()
                if (data != null) {
                    recyclerMain.visible()
                    progress.gone()
                    btnMain.gone()
                    setupMovieList(data)
                } else {
                    recyclerMain.gone()
                    progress.gone()
                    btnMain.visible()
                }
            }
            is Error -> {
                showToast(this, uiState.error.message ?: getString(R.string.message_error_not_found))
                progress.gone()
                btnMain.visible()
            }
            is NoInternet -> {
                showToast(this, getString(R.string.message_error_connection))
                progress.gone()
                btnMain.visible()
            }
        }
    }

    /**
     * This is a callback of the recycler listener.
     * {@link OnRecyclerItemClickListener}.
     * Is being triggered when an item has been clicked.
     *
     * @param position clicked position
     */
    override fun onItemClick(position: Int) {
        showToast(this, mainAdapter.getItem(position).title ?: getString(R.string.message_error_not_found))
    }

    private fun setupMovieList(data: List<MoviesResult>) {
        recyclerMain.apply {
            gridList()
            adapter = mainAdapter
        }

        mainAdapter.items = data
    }
}