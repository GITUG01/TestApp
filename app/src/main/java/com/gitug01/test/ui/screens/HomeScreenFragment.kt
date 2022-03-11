package com.gitug01.test.ui.screens

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gitug01.test.R
import com.gitug01.test.data.retrofit.TaskApi
import com.gitug01.test.domain.ImageEntity
import com.gitug01.test.domain.MainContract
import com.gitug01.test.domain.MainPresenter
import com.gitug01.test.domain.app
import com.gitug01.test.ui.ImageAdapter

class HomeScreenFragment : Fragment(), MainContract.View, OnImageClickListener {

    private lateinit var imageRecyclerView: RecyclerView
    private val imageAdapter: ImageAdapter = ImageAdapter(this)

    lateinit var api: TaskApi

    private val mPresenter by lazy { MainPresenter(this, api) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        api = requireContext().app.di.getApi()

        mPresenter.getImages()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        prepareToWorkWithRecyclerView()
    }

    fun init(view: View) {
        imageRecyclerView = view.findViewById(R.id.image_recycler_view)
    }

    fun prepareToWorkWithRecyclerView() {

        if (requireContext().resources.configuration.smallestScreenWidthDp >= 650) {
            imageRecyclerView.layoutManager =
                GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
        } else {
            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> imageRecyclerView.layoutManager =
                    GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                Configuration.ORIENTATION_LANDSCAPE -> imageRecyclerView.layoutManager =
                    GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
                else -> return
            }
        }


        imageAdapter.setData(
            arrayListOf(

            )
        )

        imageRecyclerView.let { it.adapter = imageAdapter }
    }

    override fun setImages(data: List<ImageEntity>) {
        imageAdapter.setData(
            data
        )
    }

    override fun onClick(imagePath: String) {
        parentFragmentManager.beginTransaction()
            .add(R.id.fragment_container, ImageFragment.newInstance(imagePath))
            .hide(this)
            .addToBackStack(null)
            .commit()
    }
}

interface OnImageClickListener {
    fun onClick(imagePath: String)
}