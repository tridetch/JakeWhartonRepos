package com.example.user.jakewhartonrepos.di

import android.app.Application
import android.content.Context
import com.example.user.jakewhartonrepos.data.net.GithubService
import com.example.user.jakewhartonrepos.domain.repository.GitDataRepositoriesImpl
import com.example.user.jakewhartonrepos.domain.repository.GitDataRepository
import com.example.user.jakewhartonrepos.domain.repository.datasource.GithubApiDatasource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideGithubService(): GithubService {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(GithubService::class.java)
    }

    @Provides
    @Singleton
    fun provideGithubDataRepository(githubApiDatasource: GithubApiDatasource): GitDataRepository {
        return GitDataRepositoriesImpl(githubApiDatasource)
    }
}