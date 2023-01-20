package com.gmail.vlaskorobogatov.footballapi.ui.web

import android.content.Context
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class FootballWebViewClient(private val context: Context) : WebViewClient() {
    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
        return false
    }

    override fun shouldOverrideUrlLoading(webView: WebView, request: WebResourceRequest): Boolean {
        return false
    }
}