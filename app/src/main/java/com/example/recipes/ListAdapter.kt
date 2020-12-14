package com.example.recipes


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListAdapter(private val recipes: List<Recipe>, private val listener: ListAdapter.OnItemClickListener
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


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

            recipes[position].isClicked = true
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
       // ,View.OnClickListener
    {
          val imageView:ImageView = itemView.findViewById(R.id.imageView)
          val nameTextView:TextView = itemView.findViewById(R.id.nameTextView)
          val priceTextView :TextView= itemView.findViewById(R.id.priceTextView)
          val addButton:Button = itemView.findViewById(R.id.addButton)

        // fun bindItems(recipe: Recipe){



       //  }



//
//         override fun onClick(v: View?) {
//             val position = adapterPosition
//             if(position!=RecyclerView.NO_POSITION)
//               listener.onItemClick(position)
//             notifyItemChanged(position)
//       }


    }

    interface OnItemClickListener{
      fun onItemClick(position: Int,recipe: Recipe)
  }

//    fun getSelectedItems():List<Recipe>
//    {
//        var cartItems = arrayListOf<Recipe>()
//        for(i in recipes)
//           if (i.isClicked)
//               cartItems.add(i)
//        notifyDataSetChanged()
//       return cartItems
//
//    }

}