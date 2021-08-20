package com.keelim.orange.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.keelim.orange.common.toast
import com.keelim.orange.data.model.Friends
import com.keelim.orange.databinding.FragmentFriendsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsFragment


  : Fragment() {

  companion object {
    fun newInstance() = FriendsFragment()
  }

  private val viewModel by viewModels<FriendsViewModel>()
  private var _binding: FragmentFriendsBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentFriendsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews()
    observeData()
    viewModel.fetchData("12")
  }

  private fun initViews() {
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun observeData() = viewModel.state.observe(viewLifecycleOwner) {
    when (it) {
      is FriendsState.UnInitialized -> handleUnInitialized()
      is FriendsState.Loading -> handleLoading()
      is FriendsState.Success -> handleSuccess(it.data)
      is FriendsState.Error -> handleError()
    }
  }

  private fun handleUnInitialized() {
    //requireActivity().toast("데이터 초기화 중입니다.")
  }

  private fun handleLoading() {
    //requireActivity().toast("데이터 초기화 중입니다.")
  }

  private fun handleSuccess(data: List<Friends>) {
  }

  private fun handleError() {
    requireActivity().toast("에러가 발생했습니다. 다시 한번 로드해주세요")
  }
}
