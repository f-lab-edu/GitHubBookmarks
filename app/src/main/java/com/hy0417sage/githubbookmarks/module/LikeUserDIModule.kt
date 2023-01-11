package com.hy0417sage.githubbookmarks.module

import android.content.Context
import androidx.room.Room
import com.hy0417sage.githubbookmarks.repository.LikeUserRepository
import com.hy0417sage.githubbookmarks.repository.database.LikeUserDao
import com.hy0417sage.githubbookmarks.repository.database.LikeUserDataBase
import com.hy0417sage.githubbookmarks.repository.impl.LikeUserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LikeUserDIModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): LikeUserDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            LikeUserDataBase::class.java,
            "LikeUser.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideLikeUserDao(appDataBase: LikeUserDataBase): LikeUserDao {
        return appDataBase.getLikeUserDao()
    }

    @Singleton
    @Provides
    fun provideLikeUserRepository(
        noteDAO: LikeUserDao
    ): LikeUserRepository {
        return LikeUserRepositoryImpl(noteDAO)
    }
}
