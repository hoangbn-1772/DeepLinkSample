package com.example.deeplinksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_goto_detail -> gotoDetail()
        }
    }

    private fun initComponents() {
        btn_goto_detail?.setOnClickListener(this)
    }

    private fun gotoDetail() {
        startActivity(intentFor<DetailActivity>().singleTop())
    }
}
