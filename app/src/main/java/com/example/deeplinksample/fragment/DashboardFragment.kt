package com.example.deeplinksample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.deeplinksample.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()

        btn_back?.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_mainFragment, null)
        }
    }

    /**
     * Receive data in destination fragment
     */
    private fun updateUi() {
        arguments?.let {
            text_username?.text = it.getString(MainFragment.BUNDLE_USERNAME)
            text_address?.text = it.getString("address") ?: "updating"
        }
    }
}
