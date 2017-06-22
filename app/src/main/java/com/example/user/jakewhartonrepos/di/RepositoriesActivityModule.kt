package com.example.user.jakewhartonrepos.di

import com.example.user.jakewhartonrepos.domain.interactor.GetJWRepositoriesUseCase
import com.example.user.jakewhartonrepos.domain.repository.GitDataRepository
import com.example.user.jakewhartonrepos.presentation.presenter.Repositories.RepositoriesPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class RepositoriesActivityModule {

    @Provides
    fun provideRepositoriesPresenter(getJWRepositoriesUseCaseUseCase: GetJWRepositoriesUseCase): RepositoriesPresenter {
        return RepositoriesPresenter(getJWRepositoriesUseCaseUseCase)
    }

    @Provides
    fun provedeGetJWRepositoriesUseCase(githubDataRepository: GitDataRepository): GetJWRepositoriesUseCase {
        return GetJWRepositoriesUseCase(githubDataRepository,Schedulers.io(),AndroidSchedulers.mainThread())
    }
}