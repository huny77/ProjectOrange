package com.keelim.orange.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import coil.load
import com.google.android.material.chip.Chip
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType
import com.keelim.orange.R
import com.keelim.orange.common.toast
import com.keelim.orange.data.model.Filter
import com.keelim.orange.data.model.Search
import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.response.feed.CategoryResponse
import com.keelim.orange.databinding.FragmentFeedBinding
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import org.json.JSONObject

@AndroidEntryPoint
class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FeedViewModel>()
    private val searchRecyclerAdapter = SearchRecyclerAdapter{ uid, color ->
        findNavController().navigate(
            FeedFragmentDirections.actionFeedFragmentToDetailFragment(uid.toString(), color, null, -1)
        )
    }
    private val colors = arrayOf(
        R.color.bg_orange,
        R.color.orange,
        R.color.orange_w,
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
        observeData()
        viewModel.fetchData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            }
        )
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                val quotes = parseJson1(remoteConfig.getString("recents"))
                displayPager(quotes)
            }
        }
    }

    private fun parseJson1(json: String): List<Filter> {
        val jsonArray = JSONArray(json)
        var jsonList = emptyList<JSONObject>()
        for (index in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(index)
            jsonObject?.let {
                jsonList = jsonList + it
            }
        }

        return jsonList.map {
            Filter(
                value = it.getString("value")
            )
        }
    }

    private fun displayPager(recents: List<Filter>) {
        with(binding.chips) {
            recents.forEach { element ->
                addView(
                    Chip(requireContext()).apply {
                        setChipBackgroundColorResource(colors.random())
                        text = element.value + ">"
                    }
                )
            }
        }
    }


    private fun initViews() = with(binding) {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(searchRecycler)
        searchRecycler.setHasFixedSize(true)
        searchRecycler.adapter = searchRecyclerAdapter
        searchRecycler.layoutManager = LinearLayoutManager(requireContext())
        searchBar.setOnClickListener {
            findNavController().navigate(R.id.searchDetailFragment)
        }

        btnNoti.setOnClickListener {
            findNavController().navigate(R.id.notificationFragment)
        }

        btnProfile.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }
        val images =  arrayOf(
            R.drawable.carousel1,
            R.drawable.carousel6_gold,
            R.drawable.carousel3,
        )
        carouselView.apply {
            size = images.size
            autoPlay = true
            indicatorAnimationType = IndicatorAnimationType.THIN_WORM
            carouselOffset = OffsetType.CENTER
            setCarouselViewListener { view, position ->
                val imageView = view.findViewById<ImageView>(R.id.item_carousel)
                imageView.load(images[position])
            }
            show()
        }
        carouselView.setOnClickListener {
            findNavController().navigate(R.id.eventFragment)
        }

        btnCreate.setOnClickListener {
            findNavController().navigate(R.id.createChallengeFragmentDialog)
        }
    }

    private fun observeData() = viewModel.state.observe(viewLifecycleOwner) {
        when (it) {
            is FeedState.UnInitialized -> handleUnInitialized()
            is FeedState.Loading -> handleLoading()
            is FeedState.Success -> handleSuccess(it.data1, it.data2)
            is FeedState.Error -> handleError()
        }
    }

    private fun handleUnInitialized() {
        //requireActivity().toast("데이터 초기화 중입니다.")
    }

    private fun handleLoading() {
        //requireActivity().toast("데이터 초기화 중입니다.")
    }

    private fun handleSuccess(data1: List<CategoryResponse>, data2: List<Search2>) {
        data1.forEach {
            binding.chips.addView(
                Chip(requireContext()).apply {
                    text = it.categoryName
                    setChipBackgroundColorResource(colors.random())
                }
            )
        }
        searchRecyclerAdapter.submitList(emptyList())

        val data3 = data2.map {
            Search(
                challengeId = it.challengeId,
                title = it.challengeTitle,
                description = it.challengeDescribe
            )
        }
        searchRecyclerAdapter.submitList(data3)
    }

    private fun handleError() {
        requireActivity().toast("에러가 발생했습니다. 다시 한번 로드해주세요")
    }

    companion object {
        fun newInstance() = FeedFragment()
    }
}
