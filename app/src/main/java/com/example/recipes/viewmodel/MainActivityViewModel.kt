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

       //Recipes fetched from API
       val recipes by lazy { MutableLiveData<ArrayList<Recipe>>() }
       //The current Recipe selected by the User (Used for the displaying the details fragment )
       private val currentRecipe by lazy { MutableLiveData<Recipe>() }
      //List of items added to cart by the user
       val cartItems by lazy { MutableLiveData<ArrayList<Recipe>>() }


    //Function to fetch the recipes using retrofit
    fun getRecipes(): LiveData<ArrayList<Recipe>> {

        //API call is made only when recipes is empty
        if (recipes.value.isNullOrEmpty()) {
            var retService: ApiService =
                RetrofitInstance.getService().create(ApiService::class.java)
            val call: retrofit2.Call<List<Recipe>> = retService.getRecipes()
            call.enqueue(object : retrofit2.Callback<List<Recipe>> {
                override fun onResponse(
                    call: retrofit2.Call<List<Recipe>>,
                    response: Response<List<Recipe>>
                )

                {

                    val data = (response.body() as ArrayList<Recipe>?)!!
                    for (i in 0 until data.size) {
                        Log.i("name", data[i].name)
                        //Initially setting these fields as these are extra to API
                        data[i].isClicked = false
                        data[i].num = 0
                    }
                    //Setting the fetched value to recipes live data
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

    //Updating the recipes values when ever uses selects them
    fun updateRecipes(position: Int, r: Recipe) {
        recipes.value?.set(position, r)
    }

    //Function to return the current selected recipe
    fun getRecipe(): LiveData<Recipe> {
        return currentRecipe
    }

    //Function to set the value of the current Recipe
    fun setRecipe(position: Int) {
        var recipe = recipes.value?.get(position)
        currentRecipe.value = recipe
    }

    //Function to update the cart Items
    fun getCartItems() {
        cartItems.value = arrayListOf()
        for (i in recipes.value!!)
            if (i.isClicked)
                cartItems.value?.add(i)


    }

    //Function to fetch the total price of all the cart items
    fun getTotalValue(): Double {
        var total = 0.0
        for (i in cartItems.value!!) {
            total += i.price.toDouble().times(i.num)

        }
        return total
    }


  }

