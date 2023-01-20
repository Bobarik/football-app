package com.gmail.vlaskorobogatov.footballapi.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gmail.vlaskorobogatov.football_app.databinding.WebViewLayoutBinding
import com.gmail.vlaskorobogatov.footballapi.ui.web.FootballWebViewClient
import com.onesignal.OneSignal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : Fragment() {

    companion object {
        private const val ONESIGNAL_APP_ID = "18c4a14c-8597-4f88-b4b0-4dbcc68f7ac2"
    }

    private lateinit var binding: WebViewLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        OneSignal.initWithContext(requireContext())
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        OneSignal.promptForPushNotifications()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WebViewLayoutBinding.inflate(layoutInflater)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    } else {
                        findNavController().navigateUp()
                    }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null && !savedInstanceState.isEmpty) {
            binding.webView.restoreState(savedInstanceState)
        } else {
            binding.webView.loadUrl("https://apifootball.com/")
            with(binding.webView.settings) {
                javaScriptEnabled = true
                setSupportZoom(true)
            }
            binding.webView.webViewClient = FootballWebViewClient()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webView.saveState(outState)
    }
}