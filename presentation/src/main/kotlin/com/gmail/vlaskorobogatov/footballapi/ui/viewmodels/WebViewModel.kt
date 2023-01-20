package com.gmail.vlaskorobogatov.footballapi.ui.viewmodels

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class WebViewModel
@Inject constructor(
    @ApplicationContext appContext: Context
) : ViewModel() {
    val webClient = FootballWebViewClient(appContext)
}

