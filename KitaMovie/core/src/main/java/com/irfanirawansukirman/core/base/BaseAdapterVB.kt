package com.irfanirawansukirman.core.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.irfanirawansukirman.core.ui.recyclerview.GenericDiffUtil

/**
 * Created by crazy on 4/5/20 to long live and prosper !
 * Takes leverage of not providing that damn layout res id
 *
 * USAGE:
 * class TestViewBindingAdapter : AbstractViewBindingAdapter<TestModel, TestViewHolderShimmer, CustomizableCardViewBinding>(
::TestViewHolderShimmer, CustomizableCardViewBinding::inflate
)
 *
 */
abstract class BaseAdapterViewBinding<T, VH : RecyclerView.ViewHolder, VB : ViewBinding>(
    private val viewHolder: (binding: VB) -> VH,
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
    areItemsTheSameCallback: (old: T, new: T) -> Boolean? = { _, _ -> null },
    areContentsTheSameCallback: (old: T, new: T) -> Boolean? = { _, _ -> null }
) :
    ListAdapter<T, VH>(GenericDiffUtil(areItemsTheSameCallback, areContentsTheSameCallback)) {
    abstract fun bindItems(item: T, holder: VH, position: Int, itemCount: Int)

    var forItemClickListener: forItemClickListener<T>? = null
    var onLongClickListener: forItemClickListener<T>? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item: T = getItem(holder.adapterPosition)
        bindItems(item, holder, position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = bindingInflater.invoke(LayoutInflater.from(parent.context), parent, false)
        val holder = setViewHolder(binding)

        holder.itemView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION)
                forItemClickListener?.forItem(
                    holder.adapterPosition,
                    getItem(holder.adapterPosition),
                    it
                )
        }
        holder.itemView.setOnLongClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION)
                onLongClickListener?.forItem(
                    holder.adapterPosition,
                    getItem(holder.adapterPosition),
                    it
                )
            true
        }
        return holder
    }

    @Suppress("UNCHECKED_CAST")
    private fun setViewHolder(binding: ViewBinding): VH = viewHolder(binding as VB)
}

/**
 * Created by hristijan on 2/20/19 to long live and prosper !
 */
fun interface forItemClickListener<T> {
    fun forItem(position: Int, item: T, view: View)
}
