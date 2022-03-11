package com.gitug01.test.domain

import android.app.Application
import android.content.Context
import com.gitug01.test.di.DaggerMyComponent

class App : Application() {
    val di by lazy {
        DaggerMyComponent.create()
    }
}

val Context.app: App
    get() = applicationContext as App