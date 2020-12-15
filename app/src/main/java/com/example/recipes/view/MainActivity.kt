package com.example.recipes.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.recipes.*
import com.example.recipes.model.ApiService
import com.example.recipes.model.Recipe
import com.example.recipes.model.RetrofitInstance
import com.example.recipes.view.*
import com.example.recipes.view.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Response

class MainActivity : AppCompatActivity()
    , ListAdapter.OnItemClickListener {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var selectorFragment: Fragment
    var cartItem = arrayListOf<Recipe>()
    private lateinit var retService: ApiService
    private lateinit var recipe: ArrayList<Recipe>
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retService = RetrofitInstance.getService().create(ApiService::class.java)
        recipe = arrayListOf()
        Log.i("tag","on create called")
        val call: retrofit2.Call<List<Recipe>> = retService.getRecipes()
        call.enqueue(object : retrofit2.Callback<List<Recipe>> {
            override fun onResponse(
                call: retrofit2.Call<List<Recipe>>,
                response: Response<List<Recipe>>
            ) {
                recipe = (response.body() as ArrayList<Recipe>?)!!

                for (i in recipe) {
                    i.isClicked = false
                    i.num = 0
                }


            }

            override fun onFailure(call: retrofit2.Call<List<Recipe>>, t: Throwable) {
                Log.i("error", t.message.toString())
            }

        })

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    //startActivity(Intent(this, HomeActivity::class.java))
                    adapter = ListAdapter(recipe, this@MainActivity)
                    adapter.notifyDataSetChanged()
                    selectorFragment = HomeFragment(adapter)
                }
                R.id.nav_search -> {
                    selectorFragment = SearchFragment()

                }

                R.id.nav_cart -> {
//                    cartItem = adapter.getSelectedItems() as ArrayList<Recipe>
                    selectorFragment = CartFragment(CartAdapter(getCartItems()))

                }

                R.id.nav_profile -> selectorFragment = ProfileFragment()
            }
            if (selectorFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectorFragment).commit()
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
        recipe[position] = r
    }

    fun getCartItems(): ArrayList<Recipe> {

        for (i in recipe)
            if (i.isClicked) {

                if (!cartItem.contains(i)) {
                    i.num = 1
                    cartItem.add(i)
                }
            }

        return cartItem
    }




}