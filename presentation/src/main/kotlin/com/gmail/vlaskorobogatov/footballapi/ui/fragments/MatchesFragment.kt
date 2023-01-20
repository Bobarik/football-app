package com.gmail.vlaskorobogatov.footballapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.vlaskorobogatov.football_app.databinding.MatchesFragmentLayoutBinding
import com.gmail.vlaskorobogatov.footballapi.ui.adapter.MatchesAdapter
import com.gmail.vlaskorobogatov.footballapi.ui.viewmodels.MatchesViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MatchesFragment : Fragment() {

    private val viewModel: MatchesViewModel by viewModels()
    private lateinit var binding: MatchesFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MatchesFragmentLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeUi()
    }

    private fun subscribeUi() {
        binding.matchList.layoutManager = LinearLayoutManager(context)
        val adapter = MatchesAdapter()
        binding.matchList.adapter = adapter

        lifecycleScope.launch {
            viewModel.matches.collectLatest() { matchResult ->
                matchResult.onSuccess { matchList ->
                    adapter.submitList(matchList)
                    binding.shimmer.stopShimmer()
                    binding.shimmer.isVisible = false
                }.onFailure {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Не удалось загрузить матчи.")
                        .setMessage("Ошибка: ${it.message}")
                        .setPositiveButton("Ok") { dialog, _ ->
                            requireView().findNavController().popBackStack()
                            dialog.cancel()
                        }.show()
                }
            }
        }
    }
}