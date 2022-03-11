package com.gitug01.test.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gitug01.test.R

private const val ARG_KEY = "qwerty"

class ImageFragment : Fragment() {
    private var imagePath: String? = null

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imagePath = it.getString(ARG_KEY)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        arguments?.getString(ARG_KEY)

        Glide.with(imageView).load(arguments?.getString(ARG_KEY)).into(imageView)
    }

    private fun init(view: View) {
        imageView = view.findViewById(R.id.image_container)
    }

    companion object {
        @JvmStatic
        fun newInstance(imagePath: String) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_KEY, imagePath)
                }
            }
    }
}