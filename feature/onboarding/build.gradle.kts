plugins {
    id("dance_tracker.android.feature")
    id("dance_tracker.android.library.compose")
}

android {
    namespace = "com.novack.dance_tracker.feature.onboarding"
}

dependencies {
    implementation(libs.accompanist.permissions)
}
