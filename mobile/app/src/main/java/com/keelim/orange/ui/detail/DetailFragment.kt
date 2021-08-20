package com.keelim.orange.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.keelim.orange.common.toast
import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.model.entity.Favorite
import com.keelim.orange.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

  companion object {
    fun newInstance() = DetailFragment()
  }
  private val viewModel:DetailViewModel by viewModels()
  private var _binding: FragmentDetailBinding? = null
  private val binding get() = _binding!!
  private val args by navArgs<DetailFragmentArgs>()
  private lateinit var have: Search2

  private val userId by lazy {
    val pref = requireActivity().getSharedPreferences("userId", AppCompatActivity.MODE_PRIVATE)
    return@lazy pref.getInt("userId", 20)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentDetailBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews()
    observeData()
    viewModel.fetchData(args.uid.toInt())
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun observeData() = viewModel.state.observe(viewLifecycleOwner) {
    when (it) {
      is DetailState.UnInitialized -> handleUnInitialized()
      is DetailState.Loading -> handleLoading()
      is DetailState.Success -> handleSuccess(it.data)
      is DetailState.Error -> handleError()
    }
  }

  private fun handleUnInitialized() {
    //requireActivity().toast("데이터 초기화 중입니다.")
  }

  private fun handleLoading() {
    //requireActivity().toast("데이터 초기화 중입니다.")
  }

  private fun handleSuccess(data: Search2) = with(binding) {
    have = data
    mainImg.load("http://i5b102.p.ssafy.io:8181/api/image/show/${data.imagePath}")
    detailTitle.text = data.challengeTitle
    detailDesc.text = data.challengeDescribe
  }

  private fun handleError() {
    requireActivity().toast("에러가 발생했습니다. 다시 한번 로드해주세요")
  }

  private fun initViews() = with(binding){
    btnHeart.setOnClickListener {
      viewModel.favoriteAdd(
        Favorite(
          have.challengeTitle,
          have.challengeDescribe,
          have.imagePath,
          have.categoryId,
        )
      )
      findNavController().navigate(
        DetailFragmentDirections.actionDetailFragmentToFightFragment(have.challengeId)
      )
      requireContext().toast("관심 목록에 추가 하였습니다. 이동합니다")
      viewModel.sign(have.challengeId, userId)
    }
  }
}
