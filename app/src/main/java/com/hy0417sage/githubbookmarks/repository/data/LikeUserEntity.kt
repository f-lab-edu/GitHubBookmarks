package com.hy0417sage.githubbookmarks.repository.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LikeUser")
data class LikeUserEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "userId") val userId: String?,
    @ColumnInfo(name = "userProfileImg") val userProfileImg: String?
)
