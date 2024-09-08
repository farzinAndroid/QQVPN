plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "pro.sparrow_team.qq"
    compileSdk = 34

    defaultConfig {
        applicationId = "pro.sparrow_team.qq"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        ndk {
            //noinspection ChromeOsAbiSupport
            abiFilters += listOf("arm64-v8a","armeabi-v7a","x86","x86_64")
//            abiFilters += listOf("arm64-v8a")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //compose navigation
    implementation (libs.composenavigation)
    //animation lottie
    implementation (libs.lottie)

    //hilt di
    implementation (libs.hilt)
    ksp(libs.hiltCompiler)
    implementation (libs.hiltNavigationCompose)

    //room
    implementation (libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)

    // v2ray core
    implementation(project(path = ":v2ray"))

    //datastore
    implementation (libs.androidx.datastore.preferences)

    // Import the BoM for the Firebase platform
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)

    // permissions
    implementation(libs.accompanist.permissions)

    //retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.logging.interceptor)

    //coil - load image from url
    implementation (libs.coil.compose)

    // country flags
    implementation(libs.worldcountrydata)


}

