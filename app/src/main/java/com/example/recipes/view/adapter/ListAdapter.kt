package com.example.recipes.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.model.Recipe
import com.example.recipes.util.ImageClick
import com.example.recipes.util.ListItemClick
import com.example.recipes.util.loadImage

class ListAdapter(private val recipes: ArrayList<Recipe>, private val listener: ListItemClick,private val imageListener:ImageClick
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    fun updateRecipeList(newRecipeList:List<Recipe>){
        recipes.addAll(newRecipeList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imageView.loadImage(recipes[position].image)
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


//        holder.imageView.setOnClickListener {
//            startActivity(it.context, Intent(it.context, DetailsActivity::class.java).putExtra("imageUrl", recipes[position].image).putExtra("content", recipes[position].description).putExtra("name", recipes[position].name), null)
//        }
          holder.imageView.setOnClickListener {
              if (imageListener!=null)
                  imageListener.onImageClick(position)

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






}