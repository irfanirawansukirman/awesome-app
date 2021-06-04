package com.irfanirawansukirman.mykmm.android

import com.irfanirawansukirman.mykmm.Result

sealed class State<out T> {
    object Loading : State<Nothing>()
    class Success<T>(val data: T) : State<T>()
    object Error : State<Nothing>()
}