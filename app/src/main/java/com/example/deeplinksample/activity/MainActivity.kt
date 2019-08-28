package com.example.deeplinksample.activity

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.deeplinksample.R
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)

        btn_goto_detail?.setOnClickListener {
            startActivity(intentFor<DetailActivity>().apply {
                action = "ACTION"
                data = Uri.parse("https://github.com/hoangbn-1772")
            })
        }

        btn_navigation_deeplink?.setOnClickListener {
            startActivity(intentFor<NavigationDeepLinkActivity>())
        }

        btn_firebase_dynamic_link?.setOnClickListener {
            startActivity(intentFor<FirebaseActivity>())
        }
    }
}
