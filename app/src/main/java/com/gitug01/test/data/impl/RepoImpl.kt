package com.gitug01.test.data.impl

import android.util.Log
import com.gitug01.test.data.retrofit.TaskApi
import com.gitug01.test.domain.ImageEntity
import com.gitug01.test.domain.MainContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class RepoImpl(val api: TaskApi, val callback: DataCallback) : MainContract.Repository {

    private val imagesResultList: Observable<String> = Observable.create { subscriber ->
        api.getImagesRx()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                result.forEach { image ->
                    subscriber.onNext(image)
                }
                subscriber.onComplete()
            }, { error ->
                error.printStackTrace()
            }, {

            })
    }

    override fun getImagesRx() {
        val filtedList: MutableList<ImageEntity> = mutableListOf()
        imagesResultList
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                Observable.just(ImageEntity(it))
            }
            .subscribe({ image ->
                filtedList.add(image)
            }, { error ->
                error.printStackTrace()
            }, {
                Log.d("rx", "callback")
                callback.getImagesCallback(filtedList)
            })
    }

    interface DataCallback {
        fun getImagesCallback(data: List<ImageEntity>)
    }
}