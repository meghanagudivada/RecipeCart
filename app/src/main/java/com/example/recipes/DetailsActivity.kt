package com.example.recipes

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import androidx.palette.graphics.Palette

class DetailsActivity : AppCompatActivity() {
    private lateinit var nameTextView: TextView
    private lateinit var contentTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var layout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        nameTextView = findViewById(R.id.nameTextView)
        contentTextView = findViewById(R.id.contentTextView)
        imageView = findViewById(R.id.imageView)
        layout = findViewById(R.id.linearLayout)

        val imageUrl = intent.getStringExtra("imageUrl")
        val description = intent.getStringExtra("content")
        val name = intent.getStringExtra("name")
        nameTextView.text = name
        contentTextView.text = description
        Glide.with(imageView.context).load(imageUrl).into(imageView)
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
                                    layout.setBackgroundColor(intColor)
                                }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }

                })
    }
}