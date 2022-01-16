# MVVM Profiles list
App displaying a list of profiles with the ability to add, remove and edit each one

[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)

## Project characteristics
* 100% [Kotlin](https://kotlinlang.org/)
* MVVM
* Flow
* [Android Jetpack](https://developer.android.com/jetpack)
* Single-activity architecture ([Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started))
* Reactive UI
* [View Binding](https://developer.android.com/topic/libraries/view-binding)
* Testing (Unit, UI)
* Static analysis tools
* Material design

## Tech-stack

<img src="assets/demo-gif.gif" width="280" align="right" hspace="20">

Min API level is set to [`21`](https://android-arsenal.com/api?level=21)

* Tech-stack
    * [Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) + [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)- perform background operations
    * [Jetpack](https://developer.android.com/jetpack)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - deal with whole in-app navigation
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
* Architecture
    * MVVM
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
* Tests
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit](https://junit.org/junit4/))
* Gradle
    * [Detekt](https://github.com/arturbosch/detekt#with-gradle)
* Debugging
   * [LeakCanary](https://github.com/square/leakcanarye)
