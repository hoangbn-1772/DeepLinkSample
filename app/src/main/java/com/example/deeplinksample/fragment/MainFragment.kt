package com.example.deeplinksample.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.deeplinksample.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_goto_dashboard -> sendData()

            R.id.btn_show_notification -> createDeepLink(v)
        }
    }

    private fun initComponents() {
        btn_goto_dashboard?.setOnClickListener(this)

        btn_show_notification?.setOnClickListener(this)
    }

    /**
     * Pass data from source fragment to other fragment
     */
    private fun sendData() {
        val bundle = bundleOf(BUNDLE_USERNAME to edt_username?.text.toString())
        findNavController().navigate(R.id.action_mainFragment_to_dashboardFragment, bundle)
    }


    private fun createDeepLink(view: View) {

        val args = Bundle().apply {
            putString(BUNDLE_USERNAME, "Sun Asterisk Viet Nam")
        }

        val pendingIntent = Navigation.findNavController(view)
            .createDeepLink()
            .setDestination(R.id.dashboardFragment)
            .setArguments(args)
            .createPendingIntent()

        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(NotificationChannel(
                CHANNEL_ID, "Deep Links", NotificationManager.IMPORTANCE_HIGH))
        }
        val builder = NotificationCompat.Builder(
            context!!, CHANNEL_ID)
            .setContentTitle("Deeplink Sample")
            .setContentText("Open app")
            .setSmallIcon(R.drawable.ic_add_alert_black_24dp)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        notificationManager.notify(0, builder.build())
    }

    companion object {
        const val BUNDLE_USERNAME = "username"

        private const val CHANNEL_ID = "Deeplink Sample"
    }
}
