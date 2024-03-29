plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.chooongg.form"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    api("com.chooongg.android:ktx:0.0.2")
    api("androidx.recyclerview:recyclerview:1.3.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("com.google.android.flexbox:flexbox:3.0.0")
    implementation("com.google.android.material:material:1.12.0-alpha03")
}