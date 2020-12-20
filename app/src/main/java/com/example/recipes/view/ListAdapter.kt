package com.example.recipes.view


import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipes.R
import com.example.recipes.model.Recipe

class ListAdapter(private val recipes: ArrayList<Recipe>, private val listener: OnItemClickListener
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {



    fun updateRecipeList(newRecipeList:List<Recipe>){
       // recipes.clear()
        recipes.addAll(newRecipeList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      //  holder.bindItems(recipes[position])

        Glide.with(holder.imageView.context).load(recipes[position].image).into(holder.imageView)
        holder.nameTextView.text =recipes[position].name
        holder.priceTextView.text =recipes[position].price + "$"
        holder.addButton.setOnClickListener(View.OnClickListener {

            if (!recipes[position].isClicked)
            {
                 recipes[position].isClicked = true
                 recipes[position].num = 1

            }
            else
            {
                recipes[position].num ++
            }

            if (listener!=null)
                listener.onItemClick(position,recipes[position])
            notifyItemChanged(position)


        })


        holder.imageView.setOnClickListener {


            startActivity(it.context, Intent(it.context, DetailsActivity::class.java).putExtra("imageUrl", recipes[position].image).putExtra("content", recipes[position].description).putExtra("name", recipes[position].name), null)
        }



    }

    override fun getItemCount(): Int {
        return recipes.size

    }


    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    {
          val imageView:ImageView = itemView.findViewById(R.id.imageView)
          val nameTextView:TextView = itemView.findViewById(R.id.nameTextView)
          val priceTextView :TextView= itemView.findViewById(R.id.priceTextView)
          val addButton:Button = itemView.findViewById(R.id.addButton)
    }

    interface OnItemClickListener {

        fun onItemClick(position: Int,recipe: Recipe)

    }




}