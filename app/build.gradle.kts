plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.nurrgo"
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.nurrgo"
        minSdk = 24
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    
    // Material Design
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.compose.material:material:1.6.3")
    implementation("androidx.compose.ui:ui:1.6.3")
    implementation("androidx.compose.foundation:foundation:1.6.3")
    
    // Navegación
    implementation("cafe.adriel.voyager:voyager-navigator:1.0.0")
    implementation("cafe.adriel.voyager:voyager-transitions:1.0.0")
    implementation("cafe.adriel.voyager:voyager-tab-navigator:1.0.0")
    
    // Networking
    implementation("io.ktor:ktor-client-core:2.3.8")
    implementation("io.ktor:ktor-client-cio:2.3.8")
    implementation("io.ktor:ktor-client-okhttp:2.3.8")
    implementation("io.ktor:ktor-client-json:2.3.8")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.8")
    implementation("io.ktor:ktor-client-websockets:2.3.8")
    
    // Serialización
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    
    // Imágenes
    implementation("io.coil-kt.coil3:coil-compose:3.1.0")
    implementation("io.coil-kt.coil3:coil-network-ktor2:3.1.0")
    implementation("io.coil-kt.coil3:coil-network-ktor3:3.1.0")
    
    // Fecha y hora
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
    implementation("network.chaintech:kmp-date-time-picker:1.0.7")
    
    // Almacenamiento de preferencias
    implementation("com.russhwolf:multiplatform-settings-no-arg:1.3.0")
    
    // Escaneo de códigos QR y barras
    implementation("com.google.mlkit:barcode-scanning:17.2.0")
    implementation("com.google.zxing:core:3.5.2")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("network.chaintech:qr-kit:3.0.6")
    
    // Mapas
    implementation("org.osmdroid:osmdroid-android:6.1.18")
    
    // Ubicación
    implementation("com.google.android.gms:play-services-location:21.0.1")
    
    // Firebase
    implementation("com.google.firebase:firebase-database-ktx:21.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.1")
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
    implementation("com.google.maps.android:maps-compose:4.3.3")
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // Ktor
    implementation("io.ktor:ktor-client-core:2.3.8")
    implementation("io.ktor:ktor-client-android:2.3.8")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.8")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.8")
    implementation("io.ktor:ktor-client-resources:2.3.8")

    // Kotlinx Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
