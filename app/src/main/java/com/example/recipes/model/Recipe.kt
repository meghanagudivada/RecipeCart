package com.example.recipes.model

data class Recipe(val id : Int,
                  val name : String,
                  val image : String,
                  val category : String,
                  val label:String,
                  val price:String,
                  val description:String,
                  var isClicked:Boolean,
                  var num:Int)
