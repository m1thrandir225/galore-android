import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.sebastijanzindl.galore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sebastijanzindl.galore"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties().apply {
            load(FileInputStream(File(rootProject.rootDir, "local.properties")))
        };

        buildConfigField("String", "SUPABASE_ANON_KEY", "\"${properties.getProperty("SUPABASE_ANON_KEY")}\"")
        buildConfigField("String", "SECRET", "\"${properties.getProperty("SECRET")}\"")
        buildConfigField("String", "SUPABASE_URL", "\"${properties.getProperty("SUPABASE_URL")}\"")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Compose
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.0")
    implementation("androidx.compose.material3:material3-adaptive:1.0.0-alpha06")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite:1.0.0-alpha03")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    //Coil
    val coilVer = "2.5.0"
    implementation("io.coil-kt:coil:$coilVer")
    implementation("io.coil-kt:coil-compose:$coilVer")


    //Retrofit
    val retrofitVer = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVer")


    //Dagger-Hilt
    val hiltVer = "2.50"
    implementation ("com.google.dagger:hilt-android:$hiltVer")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    ksp("com.google.dagger:hilt-compiler:$hiltVer")

    //Navigation
    val navVersion = "2.7.6"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    //Lottie
    val lottieVersion = "6.3.0"
    implementation ("com.airbnb.android:lottie-compose:$lottieVersion")

    //Supabase
    val supabase_version = "2.1.5"
    val ktor_version = "2.3.8"
    implementation ("io.github.jan-tennert.supabase:postgrest-kt:$supabase_version")
    implementation ("io.github.jan-tennert.supabase:storage-kt:$supabase_version")
    implementation ("io.github.jan-tennert.supabase:gotrue-kt:$supabase_version")
    implementation("io.github.jan-tennert.supabase:coil-integration:$supabase_version")
    implementation ("io.ktor:ktor-client-android:$ktor_version")
    implementation ("io.ktor:ktor-client-core:$ktor_version")
    implementation ("io.ktor:ktor-utils:$ktor_version")

    //Credential Manager
    implementation("androidx.credentials:credentials:1.3.0-alpha01")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0-alpha01")


    //Sign in with Google
    val googleid_ver = "1.1.0"

    implementation ("com.google.android.libraries.identity.googleid:googleid:$googleid_ver")

//    //Arrow
//    implementation("io.arrow-kt:arrow-core:1.2.1")
//    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.1")

}