package com.example.deeplinksample.util

import android.net.Uri
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.ShortDynamicLink

object DynamicLinkProvider {

    /**
     * Basic Dynamic Link
     */
    fun createDynamicLink(): Uri {
        val dynamicLink = FirebaseDynamicLinks.getInstance()
            .createDynamicLink()
            .setLink(Uri.parse("https://github.com/hoangbn-1772/DeepLinkSample"))
            .setDomainUriPrefix("https://buihoangbg.page.link")
            // Open links with app on Android
            .setAndroidParameters(DynamicLink.AndroidParameters.Builder().build())
            // Open links with com.example.ios on iOS
            .setIosParameters(DynamicLink.IosParameters.Builder("com.example.ios").build())
            .buildDynamicLink()

        return dynamicLink.uri
    }

    fun createShortDynamicLink() {
        var shortLink: Uri? = null

        val shortDynamicLink = FirebaseDynamicLinks.getInstance()
            .createDynamicLink()
            .setLink(Uri.parse("https://github.com/hoangbn-1772/DeepLinkSample"))
            .setDomainUriPrefix("https://buihoangbg.page.link")
            // Open links with app on Android
            .setAndroidParameters(DynamicLink.AndroidParameters.Builder().build())
            // Open links with com.example.ios on iOS
            .setIosParameters(DynamicLink.IosParameters.Builder("com.example.ios").build())
            .buildShortDynamicLink(ShortDynamicLink.Suffix.SHORT) // optional
            .addOnSuccessListener {
                // short link created
                shortLink = it.shortLink
            }
            .addOnFailureListener {
                // error create short link
            }
    }
}
