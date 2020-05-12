package com.tmastro.canadago.game

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.tmastro.canadago.database.AnimalDatabase
import com.tmastro.canadago.database.DataItem
import com.tmastro.canadago.database.DatabaseDao
import com.tmastro.canadago.formatItem
import kotlinx.coroutines.*

class GameViewModel(
    val database: DatabaseDao,
    application: Application): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var currentItem = MutableLiveData<DataItem?>()

    val items = database.getAllItems()

    val itemsString = Transformations.map(items) {items ->
        formatItem(items, application.resources)
    }

    init {
        Log.i("GameViewModel", "GameViewModel created")
        initializeCurrentItem()
    }

    private fun initializeCurrentItem() {
        uiScope.launch {
            currentItem.value = getCurrentItemFromDb()
        }
    }

    private suspend fun getCurrentItemFromDb(): DataItem? {
        return withContext(Dispatchers.IO) {
            var currentItem = database.getRecent()
            if (currentItem?.id == null){
                currentItem = null
            }
            currentItem
        }
    }

    fun onCreateItem() {
        uiScope.launch {
            val newItem = DataItem(0,"sample", 0)
            insert(newItem)
            currentItem.value = getCurrentItemFromDb()
        }
    }

    private suspend fun insert(item: DataItem) {
        withContext(Dispatchers.IO) {
            database.insert(item)
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            //currentItem.value = null
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            //database.clear()
            //Todo: set all isFound fields to 0
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("GameViewModel", "GameViewModel destroyed")
    }
}