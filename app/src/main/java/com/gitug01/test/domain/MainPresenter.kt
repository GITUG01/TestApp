package com.gitug01.test.domain

import com.gitug01.test.data.impl.RepoImpl
import com.gitug01.test.data.retrofit.TaskApi

class MainPresenter(val mView: MainContract.View, val api: TaskApi):
    MainContract.Presenter, RepoImpl.DataCallback {

    private var mRepository: MainContract.Repository = RepoImpl(api, this)

    override fun getImages() {
        mRepository.getImagesRx()
    }

    override fun getImagesCallback(data: List<ImageEntity>) {
        mView.setImages(data)
    }
}