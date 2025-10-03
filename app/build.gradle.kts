plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.superhero"
    compileSdk = 36
    buildFeatures {
        viewBinding= true
    }
    defaultConfig {
        applicationId = "com.example.superhero"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.gson)
    implementation (libs.androidx.recyclerview)
    implementation (libs.androidx.cardview)
    implementation (libs.glide)
    implementation (libs.androidx.constraintlayout.v221)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.viewmodel)
    implementation(libs.koin.androidx.compose)
    implementation(libs.skeletonlayout)
    implementation (libs.koin.core.v361)
    implementation (libs.koin.android.v361)

    // Koin ViewModel
    implementation (libs.koin.androidx.viewmodel)

    // Koin Annotations (para @KoinViewModel)
    implementation (libs.koin.annotations)
    implementation(libs.coil)

}