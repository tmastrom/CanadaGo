package com.tmastro.canadago

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.tmastro.canadago.database.AnimalDatabase
import com.tmastro.canadago.database.DatabaseDao
import com.tmastro.canadago.database.DataItem
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class AnimalDatabaseTest {
    private lateinit var dbDao: DatabaseDao
    private lateinit var db: AnimalDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AnimalDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dbDao = db.databaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetItem() {
        val item = DataItem()
        dbDao.insert(item)

        val recent = dbDao.getRecent()
        assertEquals(recent?.isFound, false)
    }
}