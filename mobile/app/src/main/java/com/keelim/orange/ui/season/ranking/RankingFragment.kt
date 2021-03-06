package com.keelim.orange.ui.season.ranking

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.keelim.orange.common.toast
import com.keelim.orange.data.model.ranking.Ranking
import com.keelim.orange.databinding.FragmentRankingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingFragment : Fragment() {
    private var _binding: FragmentRankingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RankingViewModel by viewModels()
    private val pointAdapter = RankingAdapter(
        longClick = {
            dialog(it)
        }
    )
    private val startAdapter = RankingAdapter(
        longClick = {
            dialog(it)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRankingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeData()
        viewModel.fetchData()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        startRecycler.adapter = startAdapter
        startRecycler.layoutManager = LinearLayoutManager(requireContext())

        pointRecycler.adapter = pointAdapter
        pointRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeData() = viewModel.state.observe(viewLifecycleOwner) {
        when (it) {
            is RankingState.UnInitialized -> handleUnInitialized()
            is RankingState.Loading -> handleLoading()
            is RankingState.Success1 -> handleSuccess1(it.data)
            is RankingState.Success2 -> handleSuccess2(it.data)
            is RankingState.Error -> handleError()
        }
    }

    private fun handleUnInitialized() {
        //requireActivity().toast("????????? ????????? ????????????.")
    }

    private fun handleLoading() {
        //requireActivity().toast("????????? ????????? ????????????.")
    }

    private fun handleSuccess1(data: List<Ranking>) {
        startAdapter.submitList(data)
    }

    private fun handleSuccess2(data: List<Ranking>) {
        pointAdapter.submitList(data)
    }

    private fun handleError() {
        requireActivity().toast("????????? ??????????????????. ?????? ?????? ??????????????????")
    }

    private fun dialog(ranking:Ranking) {
        AlertDialog.Builder(requireContext())
            .setTitle("?????? ?????? ??????")
            .setMessage("?????? ????????? ????????? ????????????????")
            .setPositiveButton("ok") { _, _ ->
                viewModel.favoriteAdd(ranking)
            }
            .setNegativeButton("Nope") { _, _ -> }
            .show()
    }
}