package com.tmastro.canadago.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tmastro.canadago.database.DatabaseDao
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
    private val detailItemKey: Long,
    private val dataSource: DatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(detailItemKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
