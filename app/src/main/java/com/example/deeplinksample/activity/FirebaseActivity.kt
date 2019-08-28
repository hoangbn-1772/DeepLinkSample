package com.example.deeplinksample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deeplinksample.R
import com.example.deeplinksample.util.DynamicLinkProvider
import com.google.firebase.FirebaseApp
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import kotlinx.android.synthetic.main.activity_firebase.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn

class FirebaseActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)

//        getData()
        updateUi()
    }

    private fun getData() {
        FirebaseDynamicLinks.getInstance().getDynamicLink(intent)
            .addOnSuccessListener {
                it.link?.let {
                    text_link?.text = it.toString()
                }
            }
            .addOnFailureListener {
                warn { it.message }
            }
    }

    private fun updateUi() {
        val uri = DynamicLinkProvider.createDynamicLink()
        text_dynamic_link.text = uri.toString()
    }
}
