package com.keelim.orange

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.keelim.orange.utils.ComponentLogger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class MyApplication: Application() {

    @Inject
    lateinit var componentLogger: ComponentLogger

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, TODO())
    }
}