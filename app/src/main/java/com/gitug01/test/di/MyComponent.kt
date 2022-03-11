package com.gitug01.test.di

import com.gitug01.test.data.retrofit.TaskApi
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [RetrofitModule::class])
interface MyComponent {

    fun getRetrofit(): Retrofit

    fun getApi(): TaskApi

}