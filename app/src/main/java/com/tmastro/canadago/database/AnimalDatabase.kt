package com.tmastro.canadago.database

import android.content.Context
import androidx.annotation.Nullable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataItem::class], version = 1, exportSchema = false)
abstract class AnimalDatabase : RoomDatabase() {

    abstract val databaseDao: DatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: AnimalDatabase? = null

        fun getInstance(context: Context): AnimalDatabase{
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AnimalDatabase::class.java,
                        "animals_canada_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}