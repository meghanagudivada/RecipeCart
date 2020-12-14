package com.example.recipes

import retrofit2.Call
import retrofit2.http.GET

interface ApiService  {
    @GET("he-public-data/reciped9d7b8c.json")
    fun getRecipes(): Call<List<Recipe>>
}