package com.example.user.jakewhartonrepos.di

import com.example.user.jakewhartonrepos.domain.interactor.GetJWRepositoriesUseCase
import com.example.user.jakewhartonrepos.presentation.presenter.Repositories.RepositoriesPresenter
import dagger.Module
import dagger.Provides

@Module
class RepositoriesActivityModule {

    @Provides
    fun provideRepositoriesPresenter(getJWRepositoriesUseCaseUseCase: GetJWRepositoriesUseCase): RepositoriesPresenter {
        return RepositoriesPresenter(getJWRepositoriesUseCaseUseCase)
    }
}