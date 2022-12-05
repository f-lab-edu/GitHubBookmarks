package com.hy0417sage.githubbookmarks.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity

@Database(entities = [LikeUserEntity::class], version = 1, exportSchema = false)
abstract class LikeUserDataBase : RoomDatabase() {

    abstract fun getLikeUserDao(): LikeUserDao

    companion object{

        @Volatile
        private var INSTANCE: LikeUserDataBase? = null

        fun getInstance(
            context: Context
        ) : LikeUserDataBase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LikeUserDataBase::class.java,
                    "LikeUser.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }

        }
    }
}