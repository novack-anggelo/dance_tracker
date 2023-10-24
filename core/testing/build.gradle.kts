plugins {
    id("dance_tracker.android.library")
    id("dance_tracker.android.library.compose")
    id("dance_tracker.android.hilt")
}

android {
    namespace = "com.novack.dance_tracker.core.testing"
}

dependencies {
    api(libs.accompanist.testharness)
    api(libs.androidx.activity.compose)
    api(libs.androidx.compose.ui.test)
    api(libs.androidx.test.core)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
    api(libs.hilt.android.testing)
    api(libs.junit4)
    api(libs.kotlinx.coroutines.test)
    api(libs.roborazzi)
    api(libs.robolectric.shadows)
    api(libs.turbine)

    debugApi(libs.androidx.compose.ui.testManifest)

    implementation(project(":core:common"))
    implementation(project(":core:design_system"))
    implementation(project(":core:model"))
    implementation(libs.kotlinx.datetime)
}
