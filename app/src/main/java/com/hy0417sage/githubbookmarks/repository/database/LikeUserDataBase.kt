package com.hy0417sage.githubbookmarks.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity

@Database(entities = [LikeUserEntity::class], version = 1, exportSchema = false)
abstract class LikeUserDataBase : RoomDatabase() {

    abstract fun getLikeUserDao(): LikeUserDao

}