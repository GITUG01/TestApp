package com.gitug01.test.data.retrofit

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface TaskApi {

    @GET("task-m-001/list.php")
    fun getImagesRx(
    ): Observable<List<String>>

}