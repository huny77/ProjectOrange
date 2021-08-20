package com.keelim.orange

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.keelim.orange.common.ThemeHelper
import com.keelim.orange.common.ThemeType
import com.keelim.orange.utils.ComponentLogger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class MyApplication : Application() {

    @Inject
    lateinit var componentLogger: ComponentLogger
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "20a6b46d196a9e2ffa117e7d330c038a")
        componentLogger.initialize(this)
        ThemeHelper.applyTheme(ThemeType.DEFAULT)
    }
    //TODO #1. 아티클 올리기 -> 챌린지에서
    //TODO #1. 챌린지 들어가서 정보 불러오기
    //TODO #1. 팔로우, 팔로잉
    //TODO #1. 챌린지 생성하기 - > 바텀시트
    //TODO #1. 아티클 코멘트 보여주기
    //TODO #1. 신고하기
    
}