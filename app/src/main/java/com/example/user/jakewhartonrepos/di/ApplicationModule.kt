package com.example.user.jakewhartonrepos.di

import android.app.Application
import android.content.Context
import com.example.user.jakewhartonrepos.domain.repository.GitDataRepositoriesImpl
import com.example.user.jakewhartonrepos.domain.repository.GitDataRepository
import com.example.user.jakewhartonrepos.domain.repository.datasource.MockGithubDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideGithubDataRepository(): GitDataRepository {
        return GitDataRepositoriesImpl(MockGithubDataSource())
    }
}