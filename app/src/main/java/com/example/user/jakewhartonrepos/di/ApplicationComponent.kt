package com.example.user.jakewhartonrepos.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun plusRepositoriesActivityComponent(repositoriesActivityModule: RepositoriesActivityModule): RepositoriesActivityComponent
}
