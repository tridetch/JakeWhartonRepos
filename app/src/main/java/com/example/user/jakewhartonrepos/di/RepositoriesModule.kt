package com.example.user.jakewhartonrepos.di

import com.example.user.jakewhartonrepos.domain.repository.GitDataRepository
import com.example.user.jakewhartonrepos.presentation.presenter.Repositories.RepositoriesPresenter
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    fun provideRepositoriesPresenter(gitDataRepository: GitDataRepository): RepositoriesPresenter {
        return RepositoriesPresenter(gitDataRepository)
    }
}