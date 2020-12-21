package com.example.recipes.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.model.Recipe
import com.example.recipes.viewmodel.MainActivityViewModel


class CartFragment() : Fragment(),CartAdapter.CartItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageView:ImageView
    private lateinit var button: Button
    private lateinit var viewModel: MainActivityViewModel

    private val cartAdapter = CartAdapter(arrayListOf(),this)
    private val cartItems = arrayListOf<Recipe>()
    private val cartDataObserver = Observer<ArrayList<Recipe>>{
       for (i in it){
           if (i.isClicked)
               cartItems.add(i)
       }
        cartAdapter.updateList(cartItems)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("tag", "OnCreateView from cart fragment")
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         Log.i("tag", "fragment onViewCreated called")
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        viewModel.recipes.observe(viewLifecycleOwner,cartDataObserver)
        recyclerView = view.findViewById(R.id.cartRecyclerView)
       // imageView = view.findViewById(R.id.cartEmptyImage)
        //button = view.findViewById(R.id.btn_place_order)
        recyclerView.adapter = cartAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)


    }

    override fun onItemClick() {
        var total = 0
        //for (i in cartItems)
    }



}