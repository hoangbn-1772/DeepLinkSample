package com.example.deeplinksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

class DetailActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getData()
    }

    private fun getData() {
        val action = intent?.action
        val data = intent?.data

        debug { "$action, $data" }
    }
}
