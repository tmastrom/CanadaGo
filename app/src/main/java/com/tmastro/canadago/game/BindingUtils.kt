package com.tmastro.canadago.game

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.tmastro.canadago.R
import com.tmastro.canadago.database.DataItem

@BindingAdapter("animalNameString")
fun TextView.setAnimalNameString(item: DataItem?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("isFoundString")
fun TextView.setIsFoundString(item: DataItem?) {
    item?.let {
        text = when(item.isFound) {
            0 -> "Not Found"
            1 -> "Found"
            else -> "err"
        }
    }
}

@BindingAdapter("animalImage")
fun ImageView.setAnimalImage(item: DataItem?) {
    item?.let {
        setImageResource(when (item.name) {
            "Beaver" -> R.drawable.ic_pets_black_24dp
            "Goose" -> R.drawable.ic_pets_black_24dp
            "Moose" -> R.drawable.ic_pets_black_24dp
            else -> R.drawable.ic_pets_black_24dp
        })
    }
}