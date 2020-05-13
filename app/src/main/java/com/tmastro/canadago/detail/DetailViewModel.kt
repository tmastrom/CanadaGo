package com.tmastro.canadago.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmastro.canadago.database.DataItem
import com.tmastro.canadago.database.DatabaseDao
import kotlinx.coroutines.Job

class DetailViewModel(
    private val detailItemKey: Long = 0L,
    dataSource: DatabaseDao) : ViewModel() {

    val database = dataSource

    private val viewModelJob = Job()

    private val item : LiveData<DataItem>

    fun getItem() = item

    init {
        item = database.getItemId(detailItemKey)
    }

    private val _navigateToGameFragment = MutableLiveData<Boolean?>()

    val navigateToGameFragment: LiveData<Boolean?>
        get() = _navigateToGameFragment

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doneNavigating() {
        _navigateToGameFragment.value = null
    }

    fun onClose() {
        _navigateToGameFragment.value = true
    }




}