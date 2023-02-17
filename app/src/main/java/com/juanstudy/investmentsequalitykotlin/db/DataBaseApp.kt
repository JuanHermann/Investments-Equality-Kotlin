package com.juanstudy.investmentsequalitykotlin.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juanstudy.investmentsequalitykotlin.MainActivity
import com.juanstudy.investmentsequalitykotlin.models.Asset

@Database(entities = [Asset::class], version = 1, exportSchema = false)
abstract class DatabaseApp : RoomDatabase() {
    abstract fun assetDao(): AssetDao

    companion object {
        private const val DATABASE_APP = "database_app"
        private var INSTANCE: DatabaseApp? = null
        fun getDatabase(context: Context): DatabaseApp {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context,DatabaseApp::class.java, DATABASE_APP)
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}