package com.juanstudy.investmentsequalitykotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juanstudy.investmentsequalitykotlin.models.Asset

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Asset::class), version = 1, exportSchema = false)
abstract class DatabaseApp : RoomDatabase() {

    abstract fun assetDao(): AssetDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DatabaseApp? = null

        fun getDatabase(context: Context): DatabaseApp {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseApp::class.java,
                    "database_app"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}