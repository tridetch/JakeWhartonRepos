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

    private var mRepositoriesActivityComponent: RepositoriesActivityComponent? = null


    override fun onCreate() {
        super.onCreate()
        instance = this
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }

    fun getRepositoriesActivityComponent(): RepositoriesActivityComponent {
        if (mRepositoriesActivityComponent == null) {
            mRepositoriesActivityComponent = mApplicationComponent.plusRepositoriesActivityComponent(RepositoriesActivityModule())
        }

        return mRepositoriesActivityComponent as RepositoriesActivityComponent
    }
}