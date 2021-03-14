package com.irfanirawansukirman.core.util.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.irfanirawansukirman.core.base.BaseAdapterViewBinding
import com.irfanirawansukirman.core.ui.recyclerview.GridSpacingItemDecoration

/**
 * Created by crazy on 3/9/20 to long live and prosper !
 */
inline fun <reified T, VH : RecyclerView.ViewHolder, VB : ViewBinding> generateRecycler(
    noinline viewHolder: (binding: VB) -> VH,
    noinline bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
    noinline areItemsTheSameCallback: (old: T, new: T) -> Boolean? = { _, _ -> null },
    noinline areContentsTheSameCallback: (old: T, new: T) -> Boolean? = { _, _ -> null },
    crossinline binder: (item: T, holder: VH, position: Int, itemCount: Int) -> Unit
): BaseAdapterViewBinding<T, VH, VB> {

    return object : BaseAdapterViewBinding<T, VH, VB>(
        viewHolder,
        bindingInflater,
        areItemsTheSameCallback,
        areContentsTheSameCallback
    ) {
        override fun bindItems(item: T, holder: VH, position: Int, itemCount: Int) {
            binder(item, holder, position, itemCount)
        }
    }
}


inline fun <reified T, VH : RecyclerView.ViewHolder, VB : ViewBinding> RecyclerView.generateVerticalAdapter(
    isGrid: Boolean = false,
    numOfColumns: Int = 1,
    spacingGridItem: Int = 8.toDp(),
    isIncludeEdge: Boolean = true,
    noinline viewHolder: (binding: VB) -> VH,
    noinline bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
    noinline areItemsTheSameCallback: (old: T, new: T) -> Boolean? = { _, _ -> null },
    noinline areContentsTheSameCallback: (old: T, new: T) -> Boolean? = { _, _ -> null },
    crossinline binder: (item: T, holder: VH, position: Int, itemCount: Int) -> Unit,
    hasFixedSize: Boolean = false, reverseLayout: Boolean = false
): BaseAdapterViewBinding<T, VH, VB> {

    val adapter = generateRecycler(
        viewHolder,
        bindingInflater,
        areItemsTheSameCallback,
        areContentsTheSameCallback,
        binder
    )
    if (!isGrid) {
        initRecyclerViewAdapter(adapter, RecyclerView.VERTICAL, hasFixedSize, reverseLayout)
    } else {
        val gridLayoutManager =
            GridLayoutManager(context, numOfColumns, GridLayoutManager.VERTICAL, reverseLayout)
        initRecyclerViewAdapter(
            adapter,
            gridLayoutManager,
            hasFixedSize,
            numOfColumns,
            spacingGridItem,
            isIncludeEdge
        )
    }
    return adapter
}

inline fun <reified T, VH : RecyclerView.ViewHolder, VB : ViewBinding> RecyclerView.generateHorizontalAdapter(
    noinline viewHolder: (binding: VB) -> VH,
    noinline bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
    noinline areItemsTheSameCallback: (old: T, new: T) -> Boolean? = { _, _ -> null },
    noinline areContentsTheSameCallback: (old: T, new: T) -> Boolean? = { _, _ -> null },
    crossinline binder: (item: T, holder: VH, position: Int, itemCount: Int) -> Unit,
    hasFixedSize: Boolean = false, reverseLayout: Boolean = false
): BaseAdapterViewBinding<T, VH, VB> {

    val adapter = generateRecycler(
        viewHolder,
        bindingInflater,
        areItemsTheSameCallback,
        areContentsTheSameCallback,
        binder
    )
    initRecyclerViewAdapter(adapter, RecyclerView.HORIZONTAL, hasFixedSize, reverseLayout)
    return adapter
}

/**Set adapter of recyclerView
 * @param yourAdapter your adapter(must extend RecyclerView.Adapter)
 * @param layoutOrientation LinearLayoutManager orientation of adapter, default is RecyclerView.VERTICAL
 * @param fixedSize isFixed size of recyclerView, default is true*/
fun <T : RecyclerView.Adapter<*>> RecyclerView.initRecyclerViewAdapter(
    yourAdapter: T,
    layoutOrientation: Int = RecyclerView.VERTICAL,
    fixedSize: Boolean = false,
    reverseLayout: Boolean = false
) {
    apply {
        layoutManager = LinearLayoutManager(context, layoutOrientation, reverseLayout)
        adapter = yourAdapter
        setHasFixedSize(fixedSize)
    }
}


/**Set adapter of recyclerView
 * @param yourAdapter your adapter(must extend RecyclerView.Adapter)
 * @param yourLayoutManager Pass your own layout manager
 * @param fixedSize isFixed size of recyclerView, default is true*/
fun <T : RecyclerView.Adapter<*>> RecyclerView.initRecyclerViewAdapter(
    yourAdapter: T,
    yourLayoutManager: RecyclerView.LayoutManager,
    fixedSize: Boolean = false,
    numOfColumns: Int,
    spacingGridItem: Int,
    isIncludeEdge: Boolean
) {
    apply {
        layoutManager = yourLayoutManager
        adapter = yourAdapter
        addItemDecoration(
            GridSpacingItemDecoration(
                numOfColumns,
                spacingGridItem,
                isIncludeEdge,
                0
            )
        )
        setHasFixedSize(fixedSize)
    }
}