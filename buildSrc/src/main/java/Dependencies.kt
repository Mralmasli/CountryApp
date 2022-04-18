import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * To define plugins
 */
object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

/**
 * To define dependencies
 */
object Deps {

    object Kotlin {
        private const val version = "1.5.0"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    }

    object Appcompat {
        private const val version = "1.4.1"
        const val appcompat = "androidx.appcompat:appcompat:$version"
    }

    object CoreKTX {
        private const val version = "1.7.0"
        private const val coroutinesVersion = "1.6.0"

        const val coreKTX = "androidx.core:core-ktx:$version"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        const val coroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion"
    }

    object Lifecycle {
        const val lifecycle = "androidx.lifecycle:lifecycle-extensions:2.2.0"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
        const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:2.4.0"
    }

    object Navigation {
        private const val version = "2.4.2"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
        const val ktx = "androidx.navigation:navigation-ui-ktx:$version"
    }

    object View {
        private const val materialVersion = "1.5.0"
        private const val constraintVersion = "2.1.3"
        private const val lottieVersion = "3.4.0"

        const val materialDesign = "com.google.android.material:material:$materialVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintVersion"
        const val percentLayout = "androidx.percentlayout:percentlayout:1.0.0"
        const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
        const val recyclerViewX = "androidx.recyclerview:recyclerview:1.2.1"
    }

    object Timber {
        private const val timberVersion = "4.7.1"
        const val timber = "com.jakewharton.timber:timber:$timberVersion"
    }

    object Dagger {
        private const val version = "2.40.5"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
        const val processor = "com.google.dagger:hilt-android-compiler:$version"
        const val jetpackProcessor = "androidx.hilt:hilt-compiler:1.0.0-beta01"
        const val fragment = "androidx.hilt:hilt-navigation-fragment:1.0.0-beta01"
    }

    //todo add apollo for graphQl and coroutines api
    object Network {
        //Retrofit
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"
        const val okHttp = "com.squareup.okhttp3:okhttp:4.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val retrofitConverter = "com.squareup.retrofit2:converter-gson:2.9.0"

        // Apollo GraphQL
        const val apollo = "com.apollographql.apollo:apollo-runtime:2.5.4"
        // optional: for coroutines support
        const val apolloCoroutine = "com.apollographql.apollo:apollo-coroutines-support:2.5.4"
    }

    val appLibraries = arrayListOf<String>().apply {
        add(Kotlin.kotlin)
        add(Appcompat.appcompat)
        add(CoreKTX.coreKTX)
        add(CoreKTX.coroutines)
        add(CoreKTX.coroutinesAndroid)
        add(CoreKTX.coroutinesPlayServices)
        add(Lifecycle.lifecycle)
        add(Lifecycle.lifecycleLiveData)
        add(Lifecycle.lifecycleViewModel)
        add(View.constraintLayout)
        add(View.materialDesign)
        add(View.legacy)
        add(View.percentLayout)
        add(View.recyclerViewX)
        add(Timber.timber)
        add(Dagger.fragment)
        add(Dagger.hilt)
        add(Dagger.fragment)
        add(Network.retrofit)
        add(Network.apollo)
        add(Network.apolloCoroutine)
        add(Network.loggingInterceptor)
        add(Network.okHttp)
        add(Network.retrofitConverter)
        add(Navigation.fragment)
        add(Navigation.ktx)
    }

    val appKaptLibraries = arrayListOf<String>().apply {
        add(Dagger.jetpackProcessor)
        add(Dagger.processor)
    }

}

fun DependencyHandler.implementation(list: List<String>){
    list.forEach { dependency ->
        add("implementation",dependency)
    }
}

fun DependencyHandler.kapt(list: List<String>){
    list.forEach { dependency ->
        add("kapt",dependency)
    }
}