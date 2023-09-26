
import com.novack.dance_tracker.DanceTrackerBuildType

plugins {
    id("dance_tracker.android.application")
    id("dance_tracker.android.application.compose")
    id("dance_tracker.android.hilt")
}

android {
    namespace = "com.novack.dance_tracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.novack.dance_tracker"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = DanceTrackerBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
}
