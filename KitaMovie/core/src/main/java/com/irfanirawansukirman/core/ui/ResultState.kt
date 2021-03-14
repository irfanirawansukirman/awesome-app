package com.irfanirawansukirman.core.ui

sealed class UIState<out T : Any> {
    data class Loading(val isLoading: Boolean) : UIState<Nothing>()
    data class Success<out T : Any>(val output: T) : UIState<T>()
    data class Failure(val throwable: Throwable) : UIState<Nothing>()
}

sealed class IOTaskResult<out T : Any> {
    data class OnSuccess<out T : Any>(val data: T) : IOTaskResult<T>()
    data class OnFailed(val throwable: Throwable) : IOTaskResult<Nothing>()
}