package com.example.deeplinksample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deeplinksample.R
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.AnkoLogger

class DetailActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getData()
    }

    private fun getData() {
        val action = intent?.action
        val data = intent?.data

        text_action?.text = action
        text_uri?.text = data.toString()
    }
}
