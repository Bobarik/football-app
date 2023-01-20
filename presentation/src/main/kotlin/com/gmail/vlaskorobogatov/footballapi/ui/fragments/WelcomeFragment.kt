package com.gmail.vlaskorobogatov.footballapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gmail.vlaskorobogatov.football_app.R
import com.gmail.vlaskorobogatov.football_app.databinding.WelcomeFragmentLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    private lateinit var binding: WelcomeFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WelcomeFragmentLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.welcomeMatchesButton.setOnClickListener {
            view.findNavController().navigate(R.id.matches_fragment)
        }

        binding.welcomeWebViewButton.setOnClickListener {
            view.findNavController().navigate(R.id.web_view_fragment)
        }
    }
}