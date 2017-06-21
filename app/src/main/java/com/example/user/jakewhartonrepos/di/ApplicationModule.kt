package com.example.user.jakewhartonrepos.di

import android.app.Application
import android.content.Context
import com.example.user.jakewhartonrepos.domain.GitDataRepositoriesImpl
import com.example.user.jakewhartonrepos.domain.GitDataRepository
import com.example.user.jakewhartonrepos.domain.datasource.MockGithubDataSource
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