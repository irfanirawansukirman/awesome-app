package com.irfanirawansukirman.core.util.extension

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.irfanirawansukirman.core.ui.progress.ProgressDialogFragment

private var progress: ProgressDialogFragment? = null

fun AppCompatActivity.initProgressDialog() {
    if (progress == null) {
        progress = ProgressDialogFragment.newInstance()
        progress?.isCancelable = false
    }
}

fun AppCompatActivity.showProgress() {
    progress?.show(supportFragmentManager, progress?.tag)
}

fun AppCompatActivity.hideProgress() {
    progress?.dismiss()
}

inline fun <reified T : Any> AppCompatActivity.extra(key: String, default: T? = null) = lazy {
    val value = intent?.extras?.get(key)
    if (value is T) value else default
}

fun AppCompatActivity.navigationToBrowser(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

inline fun <reified T : AppCompatActivity> AppCompatActivity.navigation(withFinish: Boolean = false) {
    navigation<T>(withFinish = withFinish) {}
}

inline fun <reified T : AppCompatActivity> AppCompatActivity.navigation(
    withFinish: Boolean = false,
    requestCode: Int = 0,
    intentParams: Intent.() -> Unit
) {
    val intent = Intent(this, T::class.java)
    intent.intentParams()
    if (requestCode != 0) startActivityForResult(intent, requestCode) else startActivity(intent)
    if (withFinish) finish()
}