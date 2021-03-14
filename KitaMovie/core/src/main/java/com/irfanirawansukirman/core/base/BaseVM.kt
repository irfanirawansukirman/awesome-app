package com.irfanirawansukirman.core.base

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfanirawansukirman.core.util.coroutine.CoroutineContextProvider
import com.irfanirawansukirman.core.util.extension.isNetworkAvailable
import com.irfanirawansukirman.core.util.extension.orDefault
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.io.IOException

abstract class BaseVM(
    private val application: Application,
    private val coroutineContextProvider: CoroutineContextProvider,
) : ViewModel() {

    private val _timeoutException = MutableLiveData<String>()
    val timeoutException: LiveData<String>
        get() = _timeoutException

    private val _errorException = MutableLiveData<String>()
    val errorException: LiveData<String>
        get() = _errorException

    private val _ioException = MutableLiveData<String>()
    val ioException: LiveData<String>
        get() = _ioException

    fun executeJob(
        defaultTimeOut: Long = 10_000,
        execute: suspend () -> Unit
    ) {
        viewModelScope.launch {
            if (isNetworkAvailable(application)) {
                try {
                    withTimeout(defaultTimeOut) {
                        execute()
                    }
                } catch (e: TimeoutCancellationException) {
                    _timeoutException.postValue(e.message.orDefault("Request Timeout"))
                } catch (e: IOException) {
                    _ioException.postValue(e.message.orDefault("No Internet Connection"))
                } catch (e: Exception) {
                    _errorException.postValue(e.message.orDefault("Something Went Wrong"))
                }
            } else {
                _errorException.postValue("No Internet Connection")
            }
        }
    }

    suspend fun switchToMain(action: () -> Unit) {
        withContext(coroutineContextProvider.main) { action() }
    }
}
