package com.example.user.jakewhartonrepos.di

import com.example.user.jakewhartonrepos.domain.interactor.getJWRepositories
import com.example.user.jakewhartonrepos.presentation.presenter.Repositories.RepositoriesPresenter
import dagger.Module
import dagger.Provides

@Module
class RepositoriesActivityModule {

    @Provides
    fun provideRepositoriesPresenter(getJWRepositoriesUseCase: getJWRepositories): RepositoriesPresenter {
        return RepositoriesPresenter(getJWRepositoriesUseCase)
    }
}