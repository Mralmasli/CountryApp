import org.gradle.api.artifacts.dsl.DependencyHandler

object Testing {
    private const val jUnitVersion = "4.12"
    private const val androidJUnitVersion = "1.1.2"
    private const val espressoVersion = "3.1.0"
    //For runBlockingTest, CoroutineDispatcher etc.
    private const val coroutinesTestVersion = "1.6.0"
    //For InstantTaskExecutorRule
    private const val coreVersion = "2.1.0"

    private const val jUnit = "junit:junit:$jUnitVersion"
    private const val androidJUnit = "androidx.test.ext:junit:$androidJUnitVersion"
    private const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
    private const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"
    private const val core = "androidx.arch.core:core-testing:$coreVersion"
    private const val junit = "org.jetbrains.kotlin:kotlin-test-junit:1.5.10"


    val testLibraries = arrayListOf<String>().apply {
        add(jUnit)
        add(junit)
        add(coroutine)
        add(core)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(androidJUnit)
        add(espresso)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>){
    list.forEach { dependency ->
        add("androidTestImplementation",dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>){
    list.forEach { dependency ->
        add("testImplementation",dependency)
    }
}