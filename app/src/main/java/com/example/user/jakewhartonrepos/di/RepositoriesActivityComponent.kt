package com.example.user.jakewhartonrepos.di

import com.example.user.jakewhartonrepos.presentation.presenter.Repositories.RepositoriesPresenter
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(RepositoriesActivityModule::class))
interface RepositoriesActivityComponent {
    fun provideRepositoriesPresenter(): RepositoriesPresenter
}
