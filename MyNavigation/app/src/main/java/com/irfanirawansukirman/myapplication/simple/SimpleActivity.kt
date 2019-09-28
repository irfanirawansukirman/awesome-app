package com.irfanirawansukirman.myapplication.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.irfanirawansukirman.myapplication.R

class SimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        supportActionBar?.title = "Simple Navigation"
    }
}
