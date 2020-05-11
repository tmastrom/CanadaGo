package com.tmastro.canadago.database

import android.content.Context
import android.util.Log
import androidx.annotation.Nullable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [DataItem::class], version = 1, exportSchema = false)
abstract class AnimalDatabase : RoomDatabase() {

    abstract fun databaseDao(): DatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: AnimalDatabase? = null

        fun getInstance(context: Context): AnimalDatabase =
            INSTANCE ?:  synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                                AnimalDatabase::class.java,
                            "animals_canada_table.db")
                .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            ioThread {
                                getInstance(context).databaseDao().insertData(PREPOPULATE_DATA)
                                Log.i("DB", "prepopulate data")
                            }
                        }
                    })
                .fallbackToDestructiveMigration()
                .build()
        val PREPOPULATE_DATA = listOf(DataItem(0, "Moose", 0),
            DataItem(0, "Goose", 0),
            DataItem(0, "Beaver", 0))

    }
}