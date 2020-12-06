package com.yilmazvolkan.simplebitcoinapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yilmazvolkan.simplebitcoinapp.internal.DATABASE_NAME

@Database(entities = [DataEntity::class], version = 1)
abstract class BitcoinDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao

    companion object {

        @Volatile // All threads have immediate access to this property
        private var instance: BitcoinDatabase? = null

        private val LOCK = Any() // Makes sure no threads making the same thing at the same time

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BitcoinDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }
}