package com.gitug01.test.di

import com.gitug01.test.data.retrofit.TaskApi
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {
    @Provides
    fun provideImagesRetrofit() = Retrofit.Builder()
        .baseUrl("http://dev-tasks.alef.im/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @Provides
    fun provideImagesApi(retrofit: Retrofit) =
        retrofit.create(TaskApi::class.java)
}