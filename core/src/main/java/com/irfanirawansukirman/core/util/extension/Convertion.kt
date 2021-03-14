package com.irfanirawansukirman.core.util.extension

import android.content.res.Resources
import android.util.TypedValue
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun Int?.toDp() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this?.toFloat().orDefault(0.0f),
    Resources.getSystem().displayMetrics
).toInt()

inline fun <reified T> moshiToString(obj: T): String {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val adapter = moshi?.adapter(T::class.java)
    return adapter?.toJson(obj).orDefault("")
}

inline fun <reified T> moshiToObject(json: String): T {
    val moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val adapter =  moshi.adapter(T::class.java)
    return adapter.fromJson(json) as T
}

inline fun <reified T> moshiToList(json: String): List<T> {
    val moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val types = Types.newParameterizedType(List::class.java, T::class.java)
    val adapter: JsonAdapter<List<T>> = moshi.adapter(types)
    return adapter.fromJson(json).orDefault(emptyList())
}