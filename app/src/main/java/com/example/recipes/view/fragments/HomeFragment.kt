package com.example.recipes.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.databinding.FragmentHomeBinding
import com.example.recipes.model.Recipe
import com.example.recipes.util.ImageClick
import com.example.recipes.util.ListItemClick
import com.example.recipes.view.adapter.ListAdapter

import com.example.recipes.viewmodel.MainActivityViewModel


class HomeFragment() : Fragment(), ListItemClick, ImageClick {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: FragmentHomeBinding
    private val listAdapter = ListAdapter(arrayListOf(), this, this)

    //Nav controller to navigate to details page if clicked on the ImageView of an item
    private lateinit var navController: NavController

   //Observer of recipes live data
    private val recipeDataObserver = Observer<ArrayList<Recipe>> {
        listAdapter.updateRecipeList(it)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        viewModel.getRecipes().observe(viewLifecycleOwner, recipeDataObserver)

        binding.recyclerView.apply {
            adapter = listAdapter
            layoutManager = GridLayoutManager(context, 2)
        }


    }

    //If add button on the recycler View is clicked
    override fun onItemClick(position: Int, r: Recipe) {
        viewModel.updateRecipes(position, r)
        viewModel.getCartItems()
        viewModel.getTotalValue()

    }

    //If Image View of the recycler View is clicked
    override fun onImageClick(position: Int) {

        viewModel.setRecipe(position)
        navController.navigate(R.id.action_homeFragment_to_detailsFragment)
    }


}



