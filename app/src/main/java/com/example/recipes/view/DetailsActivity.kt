package com.example.recipes.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import androidx.palette.graphics.Palette
import com.example.recipes.R
import com.example.recipes.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_details)
//        setContentView(R.layout.activity_details)


        val imageUrl = intent.getStringExtra("imageUrl")
        val description = intent.getStringExtra("content")
        val name = intent.getStringExtra("name")
        binding.nameTextView.text = name
        binding.contentTextView.text = description
        Glide.with(binding.imageView.context).load(imageUrl).into(binding.imageView)
        setupBackgroundColor(imageUrl!!)
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