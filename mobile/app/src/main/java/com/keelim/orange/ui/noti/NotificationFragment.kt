package com.keelim.orange.ui.noti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.keelim.orange.common.toast
import com.keelim.orange.data.model.notification.Notification
import com.keelim.orange.databinding.FragmentNotificationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : Fragment() {
  private var _binding: FragmentNotificationBinding? = null
  private val binding get() = _binding!!
  private val viewModel by viewModels<NotificationViewModel>()

  private val userId by lazy {
    val pref = requireActivity().getSharedPreferences("userId", AppCompatActivity.MODE_PRIVATE)
    return@lazy pref.getInt("userId", -1)
  }

  private val notificationAdapter by lazy {
    NotificationAdapter(
      clickListener = {
        viewModel.deleteNoti(it)
        viewModel.fetchData(userId)
      }
    )
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentNotificationBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews()
    observeData()
    viewModel.fetchData(userId)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun observeData() = viewModel.state.observe(viewLifecycleOwner) {
    when (it) {
      is NotificationState.UnInitialized -> handleUnInitialized()
      is NotificationState.Loading -> handleLoading()
      is NotificationState.Success -> handleSuccess(it.data)
      is NotificationState.Error -> handleError()
    }
  }
  private fun initViews() = with(binding) {
    notificationRecycler.adapter = notificationAdapter
    notificationRecycler.layoutManager = LinearLayoutManager(requireContext())
  }

  private fun handleUnInitialized() {
    //requireActivity().toast("데이터 초기화 중입니다.")
  }

  private fun handleLoading() {
    //requireActivity().toast("데이터 초기화 중입니다.")
  }
  private fun handleSuccess(data: List<Notification>) {
    if(data.isEmpty()){
      binding.tvNoData.visibility = View.VISIBLE
    } else{
      binding.tvNoData.visibility = View.INVISIBLE
    }
    notificationAdapter.submitList(data)
  }

  private fun handleError() {
//    requireActivity().toast("에러가 발생했습니다. 다시 한번 로드해주세요")
  }
}
