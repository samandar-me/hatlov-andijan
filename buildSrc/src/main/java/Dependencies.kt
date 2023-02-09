object Dependencies {
    const val DaggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val KotlinVersion = "androidx.core:core-ktx:${Versions.kotlin}"
    const val Material = "com.google.android.material:material:${Versions.m1v}"
    const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val LifeCycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleKtx}"
    const val AppCompat = "androidx.appcompat:appcompat:${Versions.appComp}"
    const val JUnit = "junit:junit:${Versions.jUnit}"
    const val Espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val ExtUnit = "androidx.test.ext:junit:${Versions.test}"
    const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val AndroidCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    const val LifecycleScope = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.scope}"
    const val RuntimeScope = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.scope}"
    const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val GsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val Okhttp = "com.squareup.okhttp3:okhttp:${Versions.ok}"
    const val Interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.ok}"
    const val Hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val KaptHilt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val HiltVM = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltVm}"
    const val RoomRun = "androidx.room:room-runtime:${Versions.room}"
    const val RoomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val RoomAn = "androidx.room:room-compiler:${Versions.room}"
    const val RoomComp = "androidx.room:room-compiler:${Versions.room}"
    const val DataStore = "androidx.datastore:datastore-preferences:${Versions.compHilt}"
    const val Jsoup = "org.jsoup:jsoup:${Versions.jsoup}"
    const val Lottie = "com.airbnb.android:lottie-compose:${Versions.lottie}"
    const val Intuit = "com.intuit.sdp:sdp-android:${Versions.intuit}"
    const val Navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
    const val NavUI = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    const val NavFeature = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.nav}"
}

object Versions {
    const val hilt = "2.40.5"
    const val kotlin = "1.7.0"
    const val constraint = "2.1.4"
    const val lifeCycleKtx = "2.3.1"
    const val appComp = "1.6.0"
    const val jUnit = "4.13.2"
    const val espresso = "3.5.1"
    const val test = "1.1.5"
    const val coroutine = "1.6.4"
    const val scope = "2.5.1"
    const val retrofit = "2.9.0"
    const val ok = "5.0.0-alpha.2"
    const val room = "2.4.3"
    const val jsoup = "1.13.1"
    const val lottie = "5.2.0"
    const val hiltVm = "1.0.0-alpha03"
    const val compHilt = "1.0.0"
    const val m1v = "1.8.0"
    const val intuit = "1.0.6"
    const val nav = "2.5.3"
}