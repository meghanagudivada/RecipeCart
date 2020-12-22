package com.example.recipes.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.recipes.R


class DefaultFragment : Fragment() {

    private lateinit var imageSlider:ImageSlider
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_default, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageSlider = view.findViewById(R.id.slider)
        var slideModel = arrayListOf<SlideModel>()
        slideModel.add(SlideModel(R.drawable.image1,"Welcome To Recipe Cart", true))
        slideModel.add(SlideModel(R.drawable.image3,"Starters",true))
        slideModel.add(SlideModel(R.drawable.background,"Main Course",true))
        slideModel.add(SlideModel(R.drawable.image2,"Desserts",true))
        imageSlider.setImageList(slideModel)



    }
}