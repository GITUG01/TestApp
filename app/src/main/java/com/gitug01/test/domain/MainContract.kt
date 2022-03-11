package com.gitug01.test.domain

interface MainContract {

    interface View {
        fun setImages(data: List<ImageEntity>)
    }

    interface Presenter {
        fun getImages()
    }

    interface Repository {
        fun getImagesRx()
    }

}