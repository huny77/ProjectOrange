package com.keelim.orange.ui.fight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.keelim.orange.R
import com.keelim.orange.common.toast
import com.keelim.orange.data.model.Search2
import com.keelim.orange.databinding.FragmentFightBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FightFragment : Fragment() {

  companion object {
    fun newInstance() = FightFragment()
  }

  private val viewModel by viewModels<FightViewModel>()
  private var _binding: FragmentFightBinding? = null
  private val binding get() = _binding!!
  private val args by navArgs<FightFragmentArgs>()


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentFightBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews()
    observeData()
    viewModel.fetchData(args.challengId)
  }

  private fun initViews() = with(binding) {
    btnRank.setOnClickListener {
      findNavController().navigate(R.id.rankingFragment)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun observeData() = viewModel.state.observe(viewLifecycleOwner) {
    when (it) {
      is FightState.UnInitialized -> handleUnInitialized()
      is FightState.Loading -> handleLoading()
      is FightState.Success -> handleSuccess(it.you, it.other)
      is FightState.Error -> handleError()
    }
  }

  private fun handleUnInitialized() {
    //requireActivity().toast("데이터 초기화 중입니다.")
  }

  private fun handleLoading() {
    //requireActivity().toast("데이터 초기화 중입니다.")
  }

  private fun handleSuccess(you: Search2, other: Search2) {
    with(binding) {
      myImg.load("http://i5b102.p.ssafy.io:8181/api/image/show/${you.imagePath}")
      myImg2.load("http://i5b102.p.ssafy.io:8181/api/image/show/${you.imagePath}")
      myTitle.text = you.challengeTitle
      myDesc.text = you.challengeDescribe

      myPoint.text = "point: " + you.totalPoint.toString()
      myStartdate.text = you.startDate
      myEnddate.text = you.endDate

      youImg.load("http://i5b102.p.ssafy.io:8181/api/image/show/${other.imagePath}")
      youImg2.load("http://i5b102.p.ssafy.io:8181/api/image/show/${other.imagePath}")
      youTitle.text = other.challengeTitle
      youDesc.text = other.challengeDescribe

      youPoint.text = "point: " + other.totalPoint.toString()
      youStartdate.text = other.startDate
      youEnddate.text = other.endDate

      mySection.setOnClickListener {
        findNavController().navigate(
          FightFragmentDirections.actionFightFragmentToSeasonFragment(you.challengeId)
        )
      }

      firstSection.setOnClickListener {
        findNavController().navigate(
          FightFragmentDirections.actionFightFragmentToSeasonFragment(you.challengeId)
        )
      }

      youSection.setOnClickListener {
        findNavController().navigate(
          FightFragmentDirections.actionFightFragmentToSeasonFragment(other.challengeId)
        )
      }

      secondSection.setOnClickListener {
        findNavController().navigate(
          FightFragmentDirections.actionFightFragmentToSeasonFragment(other.challengeId)
        )
      }
    }
  }

  private fun handleError() {
    requireActivity().toast("에러가 발생했습니다. 다시 한번 로드해주세요")
  }
}
