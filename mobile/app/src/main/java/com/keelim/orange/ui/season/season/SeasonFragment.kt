package com.keelim.orange.ui.season.season

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearSnapHelper
import com.keelim.orange.R
import com.keelim.orange.common.toast
import com.keelim.orange.data.model.season.Article
import com.keelim.orange.databinding.FragmentSeasonBinding
import com.keelim.orange.ui.search.SearchDetailFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeasonFragment : Fragment() {

  private val viewModel by viewModels<SeasonViewModel>()
  private var _binding: FragmentSeasonBinding? = null
  private val binding get() = _binding!!
  private val args by navArgs<SeasonFragmentArgs>()
  private val seasonAdapter = SeasonAdapter(
    more = {
      findNavController().navigate(
        R.id.otherFragment
      )
    },
    comment = {
      findNavController().navigate(
        SeasonFragmentDirections.actionSeasonFragmentToCommentFragment(it)
      )
    }
  )


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    _binding = FragmentSeasonBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews()
    observeData()
    viewModel.fetchData(args.challengeId)
  }

  private fun initViews() = with(binding) {
    val snapHelper = LinearSnapHelper()
    seasonRecycler.apply {
      setHasFixedSize(true)
      adapter = seasonAdapter
    }
    snapHelper.attachToRecyclerView(seasonRecycler)

    addArticle.setOnClickListener {
      findNavController().navigate(
        SeasonFragmentDirections.actionSeasonFragmentToCreateFragment2(args.challengeId)
      )
    }
  }

  override fun onResume() {
    super.onResume()
    viewModel.fetchData(args.challengeId)
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
  }

  private fun observeData() = viewModel.state.observe(viewLifecycleOwner) {
    when (it) {
      is SeasonState.UnInitialized -> handleUnInitialized()
      is SeasonState.Loading -> handleLoading()
      is SeasonState.Success -> handleSuccess(it.data)
      is SeasonState.Error -> handleError()
    }
  }

  private fun handleUnInitialized() {
    //requireActivity().toast("데이터 초기화 중입니다.")
  }

  private fun handleLoading() {
    //requireActivity().toast("데이터 초기화 중입니다.")
  }

  private fun handleSuccess(data: List<Article>) {
    if (data.isEmpty()) {
      binding.noData.visibility = View.VISIBLE
    } else {
      binding.noData.visibility = View.INVISIBLE
    }
    val dataReversed = data.reversed()
    seasonAdapter.submitList(dataReversed)
  }

  private fun handleError() {
    requireActivity().toast("에러가 발생했습니다. 다시 한번 로드해주세요")
    }
}
