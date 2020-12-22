package com.example.recipes.util

import com.example.recipes.model.Recipe

interface ListItemClick {
    fun onItemClick(position: Int,recipe: Recipe)
}