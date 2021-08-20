package com.keelim.orange.ui.season.comment

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearSnapHelper
import com.keelim.orange.common.toast
import com.keelim.orange.data.model.season.Comment
import com.keelim.orange.databinding.FragmentCommentBinding
import com.keelim.orange.ui.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentFragment : Fragment() {
    private var _binding: FragmentCommentBinding? = null
    private val binding get() = _binding!!
    private val commentAdapter = CommentAdapter(

    )
    private val viewModel: CommentViewModel by viewModels()
    private val args by navArgs<CommentFragmentArgs>()

    private val userId by lazy {
        val pref = requireActivity().getSharedPreferences("userId", AppCompatActivity.MODE_PRIVATE)
        return@lazy pref.getInt("userId", 20)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authCheck()
        initViews()
        observeData()
        viewModel.fetchData(args.article.articleId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeData() = viewModel.state.observe(viewLifecycleOwner) {
        when (it) {
            is CommentState.UnInitialized -> handleUnInitialized()
            is CommentState.Loading -> handleLoading()
            is CommentState.Success -> handleSuccess(it.data)
            is CommentState.Error -> handleError()
        }
    }

    private fun initViews() = with(binding) {
        commentRecyclerview.apply {
            setHasFixedSize(true)
            adapter = commentAdapter
        }
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(commentRecyclerview)

        btnEnter.setOnClickListener {
            if(binding.username.text.toString()=="") return@setOnClickListener

            viewModel.writeComment(args.article.articleId,
                userId,
                binding.username.text.toString())
            binding.username.text.clear()
            viewModel.fetchData(args.article.articleId)
        }
    }


    private fun handleUnInitialized() {
        //requireActivity().toast("데이터 초기화 중입니다.")
    }

    private fun handleLoading() {
        //requireActivity().toast("데이터 초기화 중입니다.")
    }

    private fun handleSuccess(data: List<Comment>) {
        commentAdapter.submitList(data)
    }

    private fun handleError() {
        requireActivity().toast("에러가 발생했습니다. 다시 한번 로드해주세요")
    }

    private fun authCheck(){
        if(userId==-1){
            startActivity(Intent(requireActivity(), AuthActivity::class.java))
            requireActivity().toast("재 인증을 해주세요")
            requireActivity().finish()
        }
    }
}