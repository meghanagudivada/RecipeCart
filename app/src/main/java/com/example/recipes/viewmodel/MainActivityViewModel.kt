package com.example.recipes.viewmodel


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.recipes.model.ApiService
import com.example.recipes.model.Recipe
import com.example.recipes.model.RetrofitInstance
import com.example.recipes.view.DefaultFragment
import retrofit2.Response


class MainActivityViewModel : ViewModel() {

//     var retService: ApiService =  RetrofitInstance.getService().create(ApiService::class.java)
//     private var recipe: ArrayList<Recipe> = arrayListOf()
//     var cartItem:ArrayList<Recipe> = arrayListOf()
       var f:Fragment = DefaultFragment()
//     private val call: retrofit2.Call<List<Recipe>> = retService.getRecipes()
//     fun getData():List<Recipe> {
//        call.enqueue(object : retrofit2.Callback<List<Recipe>> {
//            override fun onResponse(
//                call: retrofit2.Call<List<Recipe>>,
//                response: Response<List<Recipe>> )
//            {
//
//                recipe = (response.body() as ArrayList<Recipe>?)!!
//
//                for (i in recipe) {
//                    Log.i("name", i.name)
//                    i.isClicked = false
//                    i.num = 0
//                }
//
//
//            }
//
//            override fun onFailure(call: retrofit2.Call<List<Recipe>>, t: Throwable) {
//                Log.i("error", t.message.toString())
//            }
//
//        })
//
//      return  recipe
//    }


    fun getCurrentFragment():Fragment{
        return f
    }
    fun setCurrentFragment(fragment: Fragment){
        f= fragment
    }
}
