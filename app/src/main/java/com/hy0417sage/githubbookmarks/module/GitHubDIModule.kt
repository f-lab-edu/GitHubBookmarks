package com.hy0417sage.githubbookmarks.module

import com.hy0417sage.githubbookmarks.network.impl.GitHubClientImpl
import com.hy0417sage.githubbookmarks.repository.GitHubRepository
import com.hy0417sage.githubbookmarks.repository.impl.GitHubRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class GitHubDIModule {
    @Provides
    fun provideClient(): Retrofit {
        return GitHubClientImpl.getGitHubBaseURL()
    }

    @Singleton
    @Provides
    fun provideGithubRepository(
        retrofit: Retrofit
    ): GitHubRepository {
        return GitHubRepositoryImpl(retrofit)
    }
}