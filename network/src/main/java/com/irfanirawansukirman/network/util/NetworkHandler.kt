package com.irfanirawansukirman.network.util

import com.irfanirawansukirman.core.ui.IOTaskResult
import com.irfanirawansukirman.core.ui.UIState
import com.irfanirawansukirman.core.util.coroutine.CoroutineContextProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.retryWhen
import retrofit2.Response
import java.io.IOException

/**
 * Utility function that works to perform a Retrofit API call and return either a success model
 * instance or an error message wrapped in an [Exception] class
 * @param messageInCaseOfError Custom error message to wrap around [IOTaskResult.OnFailed]
 * with a default value provided for flexibility
 * @param networkApiCall lambda representing a suspend function for the Retrofit API call
 * @return [IOTaskResult.OnSuccess] object of type [T], where [T] is the success object wrapped around
 * [IOTaskResult.OnSuccess] if network call is executed successfully, or [IOTaskResult.OnFailed]
 * object wrapping an [Exception] class stating the error
 * @since 1.0
 */
@ExperimentalCoroutinesApi
suspend fun <T : Any> performSafeNetworkApiCall(
    coroutineContextProvider: CoroutineContextProvider,
    messageInCaseOfError: String = "Something went wrong",
    allowRetries: Boolean = true,
    numberOfRetries: Int = 2,
    networkApiCall: NetworkAPIInvoke<T>
): Flow<IOTaskResult<T>> {
    var delayDuration = 1000L
    val delayFactor = 2
    return flow {
        val response = networkApiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(IOTaskResult.OnSuccess(it))
            }
                ?: emit(IOTaskResult.OnFailed(IOException("API call successful but empty response body")))
            return@flow
        }
        emit(
            IOTaskResult.OnFailed(
                IOException(
                    "API call failed with error - ${
                        response.errorBody()
                            ?.string() ?: messageInCaseOfError
                    }"
                )
            )
        )
        return@flow
    }.catch { e ->
        emit(IOTaskResult.OnFailed(IOException("Exception during network API call: ${e.message}")))
        return@catch
    }.retryWhen { cause, attempt ->
        if (!allowRetries || attempt > numberOfRetries || cause !is IOException) return@retryWhen false
        delay(delayDuration)
        delayDuration *= delayFactor
        return@retryWhen true
    }.flowOn(coroutineContextProvider.io)
}

/**
 * Readable naming convention for Network call lambda
 * @since 1.0
 */
typealias NetworkAPIInvoke<T> = suspend () -> Response<T>

/**
 * Util method that takes a suspend function returning a [Flow] of [IOTaskResult] as input param and returns a
 * [Flow] of [ViewState], which emits [ViewState.Loading] with true prior to performing the IO Task. If the
 * IO operation results a [IOTaskResult.OnSuccess], the result is mapped to a [ViewState.RenderSuccess] instance and emitted,
 * else a [IOTaskResult.OnFailed] is mapped to a [ViewState.RenderFailure] instance and emitted.
 * The flowable is then completed by emitting a [ViewState.Loading] with false
 */
@ExperimentalCoroutinesApi
suspend fun <T : Any> getViewStateFlowForNetworkCall(
    coroutineContextProvider: CoroutineContextProvider,
    ioOperation: suspend () -> Flow<IOTaskResult<T>>
) =
    flow {
        emit(UIState.Loading(true))
        ioOperation().map {
            when (it) {
                is IOTaskResult.OnSuccess -> UIState.Success(it.data)
                is IOTaskResult.OnFailed -> UIState.Failure(it.throwable)
            }
        }.collect {
            emit(it)
        }
        emit(UIState.Loading(false))
    }.flowOn(coroutineContextProvider.io)