plugins {
    id("dance_tracker.android.library")
    id("dance_tracker.android.hilt")
}

android {
    namespace = "com.novack.dance_tracker.core.datastore"
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    //implementation(project(":core:common"))
    //implementation(project(":core:model"))
    implementation(libs.androidx.dataStore.preferences)
    implementation(libs.kotlinx.coroutines.android)

    //testImplementation(project(":core:datastore-test"))
    //testImplementation(project(":core:testing"))
}