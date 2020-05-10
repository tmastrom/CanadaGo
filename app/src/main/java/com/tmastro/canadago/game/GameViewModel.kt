package com.tmastro.canadago.game

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.tmastro.canadago.database.AnimalDatabase
import com.tmastro.canadago.database.DatabaseDao

class GameViewModel(
    val database: DatabaseDao,
    application: Application): AndroidViewModel(application) {

    init {
        Log.i("GameViewModel", "GameViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed")
    }
}