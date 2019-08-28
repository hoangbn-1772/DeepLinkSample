package com.example.deeplinksample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.deeplinksample.R
import org.jetbrains.anko.AnkoLogger

class NavigationDeepLinkActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_deep_link)
    }
}
