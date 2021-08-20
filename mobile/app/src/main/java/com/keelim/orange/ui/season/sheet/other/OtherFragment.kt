package com.keelim.orange.ui.season.sheet.other

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keelim.orange.common.toast
import com.keelim.orange.databinding.FragmentOtherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentOtherBinding? = null
    private val binding get() = _binding!!
    private val viewModel:OtherViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtherBinding.inflate(inflater, container, false)
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
        btnSue.setOnClickListener {
            requireContext().toast("신고가 접수되었습니다. 조만간 운영진의 조치가 있습니다.")
        }

        btnLike.setOnClickListener {
            requireContext().toast("추천 해주셔서 감사합니다.")
        }

        btnAuthenticate.setOnClickListener {
            requireContext().toast("관리자만이 인증할 수 있습니다")
        }

        btnShare.setOnClickListener {
            requireActivity().startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://i5b102.p.ssafy.io/")))
            dismiss()
        }
    }
}