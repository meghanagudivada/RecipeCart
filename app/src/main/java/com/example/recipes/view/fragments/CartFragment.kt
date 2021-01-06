package com.example.recipes.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.databinding.FragmentCartBinding
import com.example.recipes.model.Recipe
import com.example.recipes.util.CartItemClick
import com.example.recipes.view.adapter.CartAdapter
import com.example.recipes.viewmodel.MainActivityViewModel


class CartFragment() : Fragment(), CartItemClick {


    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: FragmentCartBinding
    private val cartAdapter = CartAdapter(arrayListOf(), this)

    //Observer of cartItems live data
    private val cartDataObserver = Observer<ArrayList<Recipe>> {

        //Updating the cartAdapter with cart Items
        cartAdapter.updateList(it)
        calculateTotal()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        viewModel.cartItems.observe(viewLifecycleOwner, cartDataObserver)
        binding.cartRecyclerView.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(context)
        }

        calculateTotal()

    }

    //Function to update UI if the cart is empty an empty bag image is displayed
    @SuppressLint("SetTextI18n")
    private fun calculateTotal() {

        if (cartAdapter.itemCount == 0) {
            binding.apply {
                cartEmptyImage.visibility = View.VISIBLE
                button.visibility = View.GONE
                button.text = "TOTAL = 0"
            }

        } else {
            binding.apply {
                cartEmptyImage.visibility = View.GONE
                button.visibility = View.VISIBLE
            }

            binding.button.text =
                "TOTAL = " + viewModel.getTotalValue().toString().format("%.1f") + "$"
        }

    }

    //On item click on any of the cart item on the Recycler View
    override fun onItemClick() {
        viewModel.getCartItems()
        calculateTotal()
    }



}


