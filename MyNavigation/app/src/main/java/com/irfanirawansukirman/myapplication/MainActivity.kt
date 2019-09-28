package com.irfanirawansukirman.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.irfanirawansukirman.myapplication.advance.AdvanceActivity
import com.irfanirawansukirman.myapplication.simple.SimpleActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_main_simple.setOnClickListener {
            startActivity(Intent(this, SimpleActivity::class.java))
        }
        btn_main_advance.setOnClickListener {
            startActivity(Intent(this, AdvanceActivity::class.java))
        }
    }
}
