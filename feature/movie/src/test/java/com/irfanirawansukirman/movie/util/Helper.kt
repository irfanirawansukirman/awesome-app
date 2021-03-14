package com.irfanirawansukirman.movie.util

import android.content.Context
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

object Helper {
    @JvmStatic
    fun getStringFromFile(context: Context, filePath: String?): String? {
        val stream = context.classLoader.getResourceAsStream(filePath)
        var ret: String? = null
        try {
            ret = convertStreamToString(stream)
            //Make sure you close all streams.
            stream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ret
    }

    @JvmStatic
    fun getStringFromFile(filePath: String?): String? {
        val stream = javaClass.classLoader?.getResource(filePath)?.openStream()
        var ret: String? = null
        try {
            ret = convertStreamToString(stream)
            //Make sure you close all streams.
            stream?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ret
    }

    @Throws(Exception::class)
    fun convertStreamToString(`is`: InputStream?): String {
        val reader = BufferedReader(InputStreamReader(`is`))
        val sb = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            sb.append(line).append("\n")
        }
        reader.close()
        return sb.toString()
    }
}

inline fun createMap(takeParam: HashMap<String, Any>.() -> Unit) = HashMap<String, Any>().apply {
    this.takeParam()
}