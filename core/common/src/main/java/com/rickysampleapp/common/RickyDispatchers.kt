package com.rickysampleapp.common

import javax.inject.Qualifier

// TODO to real understand these two tags
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val rickyDispatcher: RickyDispatchers)

enum class RickyDispatchers {
    Default,
    IO
}