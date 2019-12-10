package com.irfanirawansukirman.cleanarchmono.ui.main.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.irfanirawansukirman.cleanarchmono.databinding.MainItemBinding
import com.irfanirawansukirman.data.network.model.MoviesResult
import com.irfanirawansukirman.lib_recyclerviewgenericadapter.BaseViewHolder
import com.irfanirawansukirman.lib_recyclerviewgenericadapter.GenericRecyclerViewAdapter
import com.irfanirawansukirman.lib_recyclerviewgenericadapter.OnRecyclerItemClickListener

class MainAdapter(context: Context, onRecyclerItemClickListener: OnRecyclerItemClickListener) :
    GenericRecyclerViewAdapter<MoviesResult, OnRecyclerItemClickListener, MainAdapter.ItemHolder>(
        context,
        onRecyclerItemClickListener
    ) {

    class ItemHolder(
        private val mainItemBinding: MainItemBinding,
        private val getListener: OnRecyclerItemClickListener
    ) :
        BaseViewHolder<MoviesResult, OnRecyclerItemClickListener>(mainItemBinding, getListener) {

        override fun onBind(item: MoviesResult?) {
            mainItemBinding.apply {
                movieTitle = item?.title ?: "Lorem Ipsum"
                root.setOnClickListener { getListener.onItemClick(adapterPosition) }
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            MainItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }
}