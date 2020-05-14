package com.tmastro.canadago.game

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmastro.canadago.R
import com.tmastro.canadago.database.DataItem
import com.tmastro.canadago.databinding.ListItemAnimalBinding
import com.tmastro.canadago.generated.callback.OnClickListener

class ItemListAdapter : ListAdapter<DataItem, ItemListAdapter.ViewHolder>(ItemListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
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
                return ViewHolder(binding)
            }
        }
    }

}

class ItemListDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

class AnimalItemListener( val clickListener: (AnimalId : Long) -> Unit) {
    fun onClick(item: DataItem) = clickListener(item.id)
}