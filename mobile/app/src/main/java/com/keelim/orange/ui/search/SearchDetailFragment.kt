package com.keelim.orange.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.keelim.orange.common.toast
import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.model.entity.History
import com.keelim.orange.data.model.samples
import com.keelim.orange.databinding.FragmentSearchDetailBinding
import com.keelim.orange.utils.SpringAddItemAnimator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDetailFragment: Fragment() {
    private var _binding: FragmentSearchDetailBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter
    private val resultAdapter = SearchRecyclerAdapter2(
        clickListener = {
            findNavController().navigate(
                SearchDetailFragmentDirections.actionSearchDetailFragmentToDetailFragment(it.challengeId.toString(), -1, it, -1)
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        searchAdapter = SearchAdapter { text->
            editQuery.setText(text)
        }
        searchResults.adapter = searchAdapter.apply {
        }
        searchResults.apply {
            setHasFixedSize(true)
            itemAnimator = SpringAddItemAnimator()
            adapter = searchAdapter.apply {
                doOnNextLayout {
                    submitList(samples)
                }
            }
        }

        history.setOnClickListener {
            searchViewModel.getAll()
        }

        history.setOnLongClickListener {
            searchAdapter.submitList(samples)
            return@setOnLongClickListener true
        }

        editQuery.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                val query = editQuery.text.toString()
                if (query.isNotEmpty()) {
                    searchViewModel.insertHistory(History(null, query, ""))
                    searchViewModel.search(query)
                } else {
                    requireContext().toast("검색어를 입력해주세요")
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false

        }
    }

    private fun observeData() = searchViewModel.state.observe(viewLifecycleOwner) {
        when (it) {
            is HistoryState.UnInitialized -> handleUnInitialized()
            is HistoryState.Loading -> handleLoading()
            is HistoryState.Success -> handleSuccess(it.data)
            is HistoryState.Error -> handleError()
            is HistoryState.SearchSuccess -> handleSearchSuccess(it.data)
        }
    }

    private fun handleUnInitialized() {
    }

    private fun handleLoading() {
//        requireActivity().toast("검색 이력을 불러오고 있습니다.")
    }

    private fun handleSuccess(data: List<History>) {
        searchAdapter.apply {
            submitList(data)
        }
    }

    private fun handleError() {
        requireActivity().toast("에러가 발생했습니다. 다시 한번 로드해주세요")
    }

    private fun handleSearchSuccess(data: List<Search2>) = with(binding) {
//        requireContext().toast(data.toString())
        if(data.isNotEmpty()){
            searchResults.visibility = View.INVISIBLE
            searchRecycler.apply {
                adapter = resultAdapter
            }
            searchRecycler.visibility = View.VISIBLE
            resultAdapter.submitList(data)
        } else {
            requireContext().toast("검색된 챌린지가 없습니다.")
        }
    }
}