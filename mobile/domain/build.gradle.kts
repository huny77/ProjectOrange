val kotlin_version: String by extra
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}
apply {
    plugin("kotlin-android")
}

listOf(
    "android.gradle",
).forEach { file ->
    apply(from = "${rootDir}/gradle/${file}")
}

dependencies {
    implementation(project(":data"))
    implementation(project(":common"))
    implementation(AndroidX.core_ktx)
    implementation(Hilt.android)
    kapt(Hilt.hilt_compiler)

    implementation(SquareUp.timber)
    implementation(Kotlin.Coroutines.android)

    implementation(Kotlin.stdlibJvm)
    testImplementation(AppTest.junit)
    androidTestImplementation(AppTest.androidJunit)
    androidTestImplementation(AppTest.espressoCore)
    androidTestImplementation(Kotlin.Coroutines.test)
    
}