package com.irfanirawansukirman.core.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

inline fun <T> LiveData<T>.subscribe(
    owner: LifecycleOwner,
    crossinline onDataReceived: (T) -> Unit
) =
    observe(owner, { onDataReceived(it) })