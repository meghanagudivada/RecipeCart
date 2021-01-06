package com.example.recipes.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton
import com.example.recipes.R
import com.example.recipes.model.Recipe
import com.example.recipes.util.CartItemClick

class CartAdapter(private val cartItems:ArrayList<Recipe>,private val listener: CartItemClick)
    : RecyclerView.Adapter<CartAdapter.ViewHolder>(){

fun updateList(newRecipeList:List<Recipe>){
        cartItems.addAll(newRecipeList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cart_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val res = cartItems[position]
        Glide.with(holder.imageView.context).load(cartItems[position].image).into(holder.imageView)
        holder.nameTextView.text = cartItems[position].name
        holder.priceTextView.text = cartItems[position].price.toDouble() .times(cartItems[position].num).toString().format("%.1f") + "$"
        holder.categoryTextView.text = cartItems[position].category
        holder.txt_amount.number = cartItems[position].num.toString()
        holder.txt_amount.setOnValueChangeListener { view, oldValue, newValue ->


            res.num = newValue
            if (newValue == 0) {
                res.isClicked=false
                cartItems.remove(res)
                notifyItemRemoved(position)
            }
            else
             holder.priceTextView.text =  res.price.toDouble() .times(newValue).toString().format("%.1f") + "$"

            if (listener!=null)
                listener.onItemClick()
        }



    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val imageView:ImageView = itemView.findViewById(R.id.itemImage)
        val nameTextView:TextView = itemView.findViewById(R.id.itemNameTextView)
        val categoryTextView:TextView = itemView.findViewById(R.id.itemCategoryTextView)
        val priceTextView:TextView = itemView.findViewById(R.id.itemPriceTextView)
        val txt_amount:ElegantNumberButton = itemView.findViewById(R.id.txt_amount)

    }

}
