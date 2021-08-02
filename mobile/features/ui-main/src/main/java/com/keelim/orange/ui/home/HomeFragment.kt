package com.keelim.orange.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.keelim.orange.ui.main.R
import com.keelim.orange.ui.main.databinding.FragmentHomeBinding
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
      findNavController().navigate(R.id.createFragment)
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
  }
}
