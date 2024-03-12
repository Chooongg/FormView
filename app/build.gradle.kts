plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.chooongg.form.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.chooongg.form.app"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("com.chooongg.android:ktx:0.0.2")
    implementation("androidx.core:core-ktx:1.13.0-alpha05")
    implementation("androidx.appcompat:appcompat:1.7.0-alpha03")
    implementation("androidx.activity:activity-ktx:1.9.0-alpha03")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-alpha01")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.0-alpha03")
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.0-alpha03")
    implementation("com.google.android.material:material:1.12.0-alpha03")
    implementation(project(":library"))
    debugImplementation("com.facebook.soloader:soloader:0.11.0")
    debugImplementation("com.facebook.flipper:flipper:0.249.0")
}