package com.irfanirawansukirman.myapplication.advance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.irfanirawansukirman.myapplication.R

class AdvanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advance)
        supportActionBar?.title = "Advance Navigation"
    }
}
