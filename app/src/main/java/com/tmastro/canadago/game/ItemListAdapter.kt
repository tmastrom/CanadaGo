package com.tmastro.canadago.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tmastro.canadago.R
import com.tmastro.canadago.database.DataItem

class ItemListAdapter: RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.animal_name)
        val isFound: TextView = itemView.findViewById(R.id.is_found)
        val img: ImageView = itemView.findViewById(R.id.animal_image)

        fun bind(item: DataItem) {
            val res = itemView.context.resources
            name.text = item.name
            isFound.text = item.isFound.toString()
            img.setImageResource(
                when (item.name) {
                    "Beaver" -> R.drawable.ic_pets_black_24dp
                    "Goose" -> R.drawable.ic_pets_black_24dp
                    "Moose" -> R.drawable.ic_pets_black_24dp
                    else -> R.drawable.ic_launcher_foreground
                }
            )
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view =
                    layoutInflater.inflate(R.layout.list_item_animal, parent, false)
                return ViewHolder(view)
            }
        }
    }

    var data = listOf<DataItem>()

        set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}