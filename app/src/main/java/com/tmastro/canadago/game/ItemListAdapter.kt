package com.tmastro.canadago.game

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmastro.canadago.R
import com.tmastro.canadago.database.DataItem
import com.tmastro.canadago.databinding.ListItemAnimalBinding

class ItemListAdapter : ListAdapter<DataItem, ItemListAdapter.ViewHolder>(ItemListDiffCallback()) {

    class ItemListDiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder private constructor(val binding: ListItemAnimalBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: DataItem) {
           binding.animal = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemAnimalBinding.inflate(layoutInflater, parent, false)
                //val view = layoutInflater.inflate(R.layout.list_item_animal, parent, false)
                return ViewHolder(binding)
            }
        }
    }

/*    var data = listOf<DataItem>()

        set(value) {
        field = value
        notifyDataSetChanged()
    }*/

    //override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}