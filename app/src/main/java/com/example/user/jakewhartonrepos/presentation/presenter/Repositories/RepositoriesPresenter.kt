package com.example.user.jakewhartonrepos.presentation.presenter.Repositories

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.user.jakewhartonrepos.domain.interactor.getJWRepositories
import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import com.example.user.jakewhartonrepos.presentation.view.Repositories.RepositoriesView
import io.reactivex.observers.DisposableObserver

@InjectViewState
class RepositoriesPresenter(val getJWRepositories: getJWRepositories) : MvpPresenter<RepositoriesView>() {

    var githubRepositoriesObserver: JwRepositoriesObserver? = null

    fun onAttach() {
        if (githubRepositoriesObserver == null) {
            Log.d("JakeWhartonRepos", "subscribe to observable")
            githubRepositoriesObserver = JwRepositoriesObserver()
            getJWRepositories.execute(observer = githubRepositoriesObserver as JwRepositoriesObserver)
        }
    }

    fun onDetach() {
        Log.d("JakeWhartonRepos", "unsubscribe from observable")
        githubRepositoriesObserver?.dispose()
    }

    inner class JwRepositoriesObserver : DisposableObserver<GithubRepositoryModel>() {
        override fun onNext(githubRepository: GithubRepositoryModel) {
            this@RepositoriesPresenter.viewState.showRepoInList(githubRepository)
        }

        override fun onError(e: Throwable?) {
            this@RepositoriesPresenter.viewState.showErrorMessage()
        }

        override fun onComplete() {
            this@RepositoriesPresenter.viewState.showCompleteMessage()
        }

    }

}
