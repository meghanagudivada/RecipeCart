package com.example.recipes.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipes.model.ApiService
import com.example.recipes.model.Recipe
import com.example.recipes.model.RetrofitInstance
import retrofit2.Response


class MainActivityViewModel(application: Application) :AndroidViewModel(application)  {

       val recipes by lazy { MutableLiveData<ArrayList<Recipe>>() }
       val currentRecipe by lazy { MutableLiveData<Recipe>() }

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

    fun getRecipe():LiveData<Recipe>{
        return currentRecipe
    }
    fun setRecipe(position: Int){
      var recipe = recipes.value?.get(position)
      currentRecipe.value = recipe
    }
  }

