package com.example.recipes.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.recipes.*
import com.example.recipes.model.ApiService
import com.example.recipes.model.Recipe
import com.example.recipes.model.RetrofitInstance
import com.example.recipes.view.*
import com.example.recipes.view.SearchFragment
import com.example.recipes.viewmodel.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Response

class MainActivity : AppCompatActivity()
    , ListAdapter.OnItemClickListener {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var selectorFragment: Fragment
    var cartItem = arrayListOf<Recipe>()
    private lateinit var recipe: ArrayList<Recipe>
    private lateinit var listadapter: ListAdapter
    private lateinit var viewModel:MainActivityViewModel
    private val recipeDataObserver = Observer<ArrayList<Recipe>>{
          recipe = it
    }
    private val cartDataObserver = Observer<ArrayList<Recipe>>{
        cartItem = it
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("tag","on create called")
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.recipes.observe(this,recipeDataObserver)
        viewModel.cartItem.observe(this,cartDataObserver)
        viewModel.getRecipes()
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {

                    listadapter = ListAdapter(recipe, this@MainActivity)
                    listadapter.notifyDataSetChanged()
                    selectorFragment = HomeFragment(listadapter)
                }
                R.id.nav_search -> {
                    selectorFragment = SearchFragment()
                }

                R.id.nav_cart -> {
                    selectorFragment = CartFragment(CartAdapter(cartItem))
                }

                R.id.nav_profile -> selectorFragment = ProfileFragment()
            }

            if (selectorFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,selectorFragment).commit()
            }
            true
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DefaultFragment())
            .commit()


    }

    override fun onStart() {
        super.onStart()
        Log.i("tag","on start called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("tag","on resume called")
    }

    override fun onItemClick(position: Int, r: Recipe) {
        viewModel.updateRecipes(position,r)
        viewModel.getCartItems()

    }





}