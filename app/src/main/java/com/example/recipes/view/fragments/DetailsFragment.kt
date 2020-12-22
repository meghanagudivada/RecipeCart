package com.example.recipes.view.fragments

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.recipes.R
import com.example.recipes.databinding.FragmentCartBinding
import com.example.recipes.databinding.FragmentDetailsBinding
import com.example.recipes.model.Recipe
import com.example.recipes.viewmodel.MainActivityViewModel

class DetailsFragment : Fragment() {

   private lateinit var binding: FragmentDetailsBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        viewModel.getRecipe().observe(viewLifecycleOwner, Observer<Recipe>{
            binding.nameTextView.text = it.name
            binding.contentTextView.text = it.description
            Glide.with(binding.imageView.context).load(it.image).into(binding.imageView)
            setupBackgroundColor(it.image!!)

        })


    }

    private  fun setupBackgroundColor(url:String){
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    Palette.from(resource)
                        .generate(){palette -> val intColor = palette?.mutedSwatch?.rgb ?:0
                            //
                            binding.linearLayout.setBackgroundColor(intColor)
                        }
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }

            })
    }
}