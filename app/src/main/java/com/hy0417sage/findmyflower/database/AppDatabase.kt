package com.hy0417sage.findmyflower.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hy0417sage.findmyflower.data.model.FlowerEntity

@Database(entities = arrayOf(FlowerEntity::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getFlowerDao(): FlowerDao

    companion object {
        private const val databaseName = "db_flower"
        private var appDatabase: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    databaseName
                ).build()
            }
            return appDatabase
        }
    }
}