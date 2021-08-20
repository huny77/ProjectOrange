package com.keelim.orange.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.keelim.orange.R
import com.keelim.orange.common.toast
import com.keelim.orange.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
  private var _binding: FragmentHomeBinding? = null
  private val binding:FragmentHomeBinding get() = _binding!!
  private val viewModel:HomeViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun initViews() = with(binding){
    btn1.setOnClickListener{
      findNavController().navigate(R.id.notificationFragment)
    }

    btn2.setOnClickListener{
      findNavController().navigate(R.id.feedFragment)
    }

    btn3.setOnClickListener{
      findNavController().navigate(R.id.createFragment2)
    }

    btn4.setOnClickListener{
      findNavController().navigate(R.id.fightFragment)
    }

    btn5.setOnClickListener{
      findNavController().navigate(R.id.otherFragment)
    }

    btn6.setOnClickListener{
      findNavController().navigate(R.id.rankingFragment)
    }

    btn7.setOnClickListener {
      findNavController().navigate(R.id.seasonFragment)
    }

    btn8.setOnClickListener {
      findNavController().navigate(R.id.searchDetailFragment)
    }

    btn9.setOnClickListener {
      val pref = requireActivity().getSharedPreferences("token", AppCompatActivity.MODE_PRIVATE)
      val token = pref.getString("token", "")
      requireActivity().toast(token!!)
    }
  }
}
