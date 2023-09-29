plugins {
    id("dance_tracker.android.library")
    id("dance_tracker.android.library.compose")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.novack.dance_tracker.core.ui"
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.runtime.livedata)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.metrics)
    api(libs.androidx.tracing.ktx)

    debugApi(libs.androidx.compose.ui.tooling)

    //implementation(project(":core:analytics"))
    implementation(project(":core:design_system"))
    //implementation(project(":core:domain"))
    //implementation(project(":core:model"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.core.ktx)
    /*implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)*/
    implementation(libs.kotlinx.datetime)

    //androidTestImplementation(project(":core:testing"))
}
