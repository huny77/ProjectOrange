package com.keelim.orange.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.keelim.orange.R
import com.keelim.orange.databinding.ActivityTutorialBinding
import com.keelim.orange.ui.auth.AuthActivity
import com.keelim.orange.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TutorialActivity : AppCompatActivity() {
    private val binding by lazy { ActivityTutorialBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        viewpager.adapter = ViewPager2Adapter()
        btnSkip.setOnClickListener {
            goNext()
        }
        btnNext.setOnClickListener {
            val current = viewpager.currentItem + 1
            if (current < 3) {
                viewpager.currentItem = current
            } else {
                goNext()
            }
        }
        TabLayoutMediator(tabIndicator, viewpager) { tab, position ->
        }.attach()
    }

    private fun goNext() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    inner class ViewPager2Adapter : FragmentStateAdapter(this) {
        private val fragments = listOf<Fragment>(
            TutorialFragment(
                R.drawable.main,
                "안녕하세요!\n 프로젝트 오렌지 입니다",
                "당신을 기다렸어요!!",
            ),
            TutorialFragment(
                R.drawable.fight,
                "경쟁의 세계에는 \n 두 마디 어휘밖에 없다. \n 이기느냐 지느냐",
                "윈스턴 처칠"
            ),
            TutorialFragment(
                R.drawable.morning,
                "당신의 습관이 \n 항상 누군가와 함께하길",
                "프로젝트 오렌지 팀이 응원합니다."
            ),
        )

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }
}