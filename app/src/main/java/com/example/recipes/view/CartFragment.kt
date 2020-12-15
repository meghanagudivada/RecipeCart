package com.example.recipes.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.model.Recipe
import com.example.recipes.view.CartAdapter


class CartFragment(private val adapter: CartAdapter) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageView:ImageView
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.i("tag","OnCreateView")
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("tag","fragment onViewCreated called")
        recyclerView = view.findViewById(R.id.cartRecyclerView)
        imageView = view.findViewById(R.id.cartEmptyImage)
        button = view.findViewById(R.id.btn_place_order)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        if (adapter.itemCount ==0) {
          imageView.visibility = View.VISIBLE
          button.visibility = View.GONE
          recyclerView.visibility = View.GONE
        }
        else
        {
         button.visibility = View.VISIBLE
         imageView.visibility = View.GONE
        }

    }


}