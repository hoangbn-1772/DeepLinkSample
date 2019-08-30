package com.example.deeplinksample.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.deeplinksample.R
import com.example.deeplinksample.util.DynamicLinkProvider
import com.google.firebase.FirebaseApp
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import kotlinx.android.synthetic.main.activity_firebase.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.warn

class FirebaseActivity : AppCompatActivity(), View.OnClickListener, AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)
        initComponents()
//        getData()
        updateUi()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_share_link -> onShareLinkClicked()
        }
    }

    private fun initComponents() {
        btn_share_link?.setOnClickListener(this)
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

    /**
     * Share link
     */
    private fun onShareLinkClicked() {
        val link = DynamicLinkProvider.generateContentLink()
        debug { link }

        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, link.toString())
            startActivity(Intent.createChooser(this, "Share link"))
        }
    }
}
