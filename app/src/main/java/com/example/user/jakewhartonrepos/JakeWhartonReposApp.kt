package com.example.user.jakewhartonrepos

import android.app.Application
import com.example.user.jakewhartonrepos.di.*

class JakeWhartonReposApp : Application() {

    companion object {
        private var instance: JakeWhartonReposApp? = null

        fun get(): JakeWhartonReposApp {
            return instance as JakeWhartonReposApp
        }
    }

    private lateinit var mApplicationComponent: ApplicationComponent

    private var mRepositoriesComponent: RepositoriesComponent? = null


    override fun onCreate() {
        super.onCreate()
        instance = this
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }

    fun getRepositoriesComponent(): RepositoriesComponent {
        if (mRepositoriesComponent == null) {
            mRepositoriesComponent = mApplicationComponent.plusRepositoriesComponent(RepositoriesModule())
        }

        return mRepositoriesComponent as RepositoriesComponent
    }
}