plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")


    //HILT plugins required
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.pierretest.firstprgapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pierretest.firstprgapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")




    //Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.compose.material:material:1.5.4")
    implementation("androidx.compose.ui:ui:1.5.4")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


    //okHttp
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")


    //Gson
    implementation("com.google.code.gson:gson:2.10.1")


    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")


    //HILT Imports
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")


    //HILTViewModels
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    runtimeOnly("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")


    //Image
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("com.google.android.material:material:1.2.0-alpha03")
    implementation("com.makeramen:roundedimageview:2.3.0")

    //test coroutine
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    //LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")

    //ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // Testing Imports
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //mockito
    testImplementation("org.mockito:mockito-core:3.8.0")
//    androidTestImplementation("org.mockito:mockito-android:2.2.0"
    testImplementation("androidx.arch.core:core-testing:2.1.0")

    androidTestImplementation("org.mockito:mockito-core:3.8.0")

    //Room Database
    implementation("androidx.room:room-runtime:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
    kapt("androidx.room:room-compiler:2.6.0")

    //ksp




}