package com.example.user.jakewhartonrepos.di

import com.example.user.jakewhartonrepos.presentation.presenter.Repositories.RepositoriesPresenter
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(RepositoriesModule::class))
interface RepositoriesComponent {
    fun provideRepositoriesPresenter(): RepositoriesPresenter
}
