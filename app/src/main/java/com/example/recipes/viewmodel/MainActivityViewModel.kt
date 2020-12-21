package com.example.recipes.viewmodel


import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipes.model.ApiService
import com.example.recipes.model.Recipe
import com.example.recipes.model.RetrofitInstance
import com.example.recipes.view.DefaultFragment
import retrofit2.Response


class MainActivityViewModel(application: Application) :AndroidViewModel(application)  {

       val recipes by lazy { MutableLiveData<ArrayList<Recipe>>() }
       private val cartItem by lazy { MutableLiveData<ArrayList<Recipe>>() }

    fun getRecipes():LiveData<ArrayList<Recipe>> {

        if (recipes.value.isNullOrEmpty()) {
            var retService: ApiService =
                RetrofitInstance.getService().create(ApiService::class.java)
            val call: retrofit2.Call<List<Recipe>> = retService.getRecipes()
            call.enqueue(object : retrofit2.Callback<List<Recipe>> {
                override fun onResponse(
                    call: retrofit2.Call<List<Recipe>>,
                    response: Response<List<Recipe>>
                ) {

                    val data = (response.body() as ArrayList<Recipe>?)!!
                    for (i in 0 until data.size) {
                        Log.i("name", data[i].name)
                        data[i].isClicked = false
                        data[i].num = 0
                    }
                    recipes.value = data
                }

                override fun onFailure(call: retrofit2.Call<List<Recipe>>, t: Throwable) {
                    Log.i("error", t.message.toString())
                    recipes.value = null
                }

            })


        }
        return recipes
    }
    fun updateRecipes(position: Int, r: Recipe ){
        recipes.value?.set(position, r)
    }

//    fun getCartItems():LiveData<ArrayList<Recipe>> {
//
//        cartItem.value = arrayListOf()
//        for (i in recipes.value!!) {
//
//            if (i.isClicked) {
//                Log.i("getCartItems", "function called")
//                cartItem.value?.add(i)
//                cartItem.value?.get(0)?.let { Log.i("getCartItems", it.name) }
//            }
//        }
//
//            return cartItem
//
//            }
//
//
  }

