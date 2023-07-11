plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

// 后期优化，配置通过版本管理导入
android {
    namespace = "com.lsy.framelib"
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.defaultConfig.minSdk
        targetSdk = AndroidConfig.defaultConfig.targetSdk

        consumerProguardFiles("consumer-rules.pro")
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
        dataBinding = true
    }
}

dependencies {
    implementation(ThirdLibs.appcompat)
    implementation(ThirdLibs.material)

    implementation(ThirdLibs.core_ktx)
    implementation(ThirdLibs.ktx_coroutines)
    implementation(ThirdLibs.viewmodel_ktx)

    implementation(ThirdLibs.retrofit2)
    implementation(ThirdLibs.logging_interceptor)
    implementation(ThirdLibs.converter_gson)

    implementation(ThirdLibs.gson)

    implementation(ThirdLibs.glide)

    implementation(ThirdLibs.timber)
}