package com.tmastro.canadago.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DatabaseDao {

    @Insert
    fun insert(item: DataItem)

    @Update
    fun update(item: DataItem)

    @Query("SELECT * from animals_canada_table WHERE id = :key")
    fun get(key: Int): DataItem?

    @Query("DELETE FROM animals_canada_table")
    fun clear()

    @Query("SELECT * FROM animals_canada_table ORDER BY id DESC LIMIT 1")
    fun getRecent(): DataItem?

    @Query("SELECT * FROM animals_canada_table ORDER BY id DESC")
    fun getAllItems(): LiveData<List<DataItem>>

    @Insert
    fun insertData(data: List<DataItem>)

    //@Query("SELECT * FROM animals_canada_table WHERE is_found = FALSE")
    //@Query("SELECT * FROM animals_canada_table WHERE is_found = TRUE")


}