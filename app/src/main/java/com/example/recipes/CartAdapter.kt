package com.example.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton

class CartAdapter(private val cartItems:ArrayList<Recipe>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.imageView.context).load(cartItems[position].image).into(holder.imageView)
        holder.nameTextView.text = cartItems[position].name
        holder.priceTextView.text = cartItems[position].price + "$"
        holder.categoryTextView.text = cartItems[position].category
        holder.txt_amount.number = 1.toString()
//        holder.txt_amount.number = cartItems[position].price
        holder.txt_amount.setOnValueChangeListener { view, oldValue, newValue ->
            if (newValue == 0) {
                cartItems.remove(cartItems[position])
                notifyDataSetChanged()
            }
            else
             holder.priceTextView.text =  cartItems[position].price.toDouble() .times(newValue).toString().format("%.1f") + "$"
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