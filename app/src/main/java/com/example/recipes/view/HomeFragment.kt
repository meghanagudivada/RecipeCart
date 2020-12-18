package com.example.recipes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.model.Recipe

import com.example.recipes.viewmodel.MainActivityViewModel


class HomeFragment() : Fragment() , ListAdapter.OnItemClickListener

{
    private lateinit var recyclerView: RecyclerView
   // private lateinit var viewModel: MainActivityViewModel
    private val viewModel: MainActivityViewModel by activityViewModels()
    private val listAdapter = ListAdapter(arrayListOf(),this)

    private val recipeDataObserver = Observer<ArrayList<Recipe>>{
        listAdapter.updateRecipeList(it)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProviders.of(requireActivity())[MainActivityViewModel::class.java]
        viewModel.recipes.observe(this, recipeDataObserver)
        viewModel.getRecipes()
        recyclerView = view.findViewById(R.id.recyclerView)

        //adapter = ListAdapter(recipe,this@Homefragment)
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)


    }
    override fun onItemClick(position: Int, r: Recipe) {
        viewModel.updateRecipes(position, r)
        viewModel.getCartItems()


    }


}



