plugins {
    id("dance_tracker.android.library")
    id("dance_tracker.android.hilt")
}

android {
    namespace = "com.novack.dance_tracker.core.common"
}

dependencies {
    implementation(libs.kotlinx.datetime)
}