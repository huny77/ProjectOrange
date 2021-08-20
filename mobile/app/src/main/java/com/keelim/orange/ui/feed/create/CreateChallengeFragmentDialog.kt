package com.keelim.orange.ui.feed.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keelim.orange.databinding.FragmentCreateChallengeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateChallengeFragmentDialog : BottomSheetDialogFragment() {
    private var _binding: FragmentCreateChallengeBinding? = null
    private val binding get() = _binding!!
    private val viewModel:CreateChallengeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateChallengeBinding.inflate(inflater, container, false)
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

    private fun initViews() = with(binding) {
        datepicker.setOnClickListener {
            showDateRangePicker()
        }
    }

    private fun showDateRangePicker() {

    }
}