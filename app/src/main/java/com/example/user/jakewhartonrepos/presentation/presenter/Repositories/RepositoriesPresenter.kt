package com.example.user.jakewhartonrepos.presentation.presenter.Repositories

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.user.jakewhartonrepos.domain.interactor.GetJWRepositoriesUseCase
import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import com.example.user.jakewhartonrepos.presentation.view.Repositories.RepositoriesView
import io.reactivex.observers.DisposableObserver

@InjectViewState
class RepositoriesPresenter(val GetJWRepositoriesUseCase: GetJWRepositoriesUseCase) : MvpPresenter<RepositoriesView>() {

    var githubRepositoriesObserver: JwRepositoriesObserver? = null

    fun onAttach() {
        if (githubRepositoriesObserver == null) {
            refreshListOfRepositories()
        }
    }

    fun onDetach() {
        Log.d("JakeWhartonRepos", "unsubscribe from observable")
        githubRepositoriesObserver?.dispose()
    }

    fun onRefreshClick() {
        refreshListOfRepositories()
    }

    private fun refreshListOfRepositories() {
        Log.d("JakeWhartonRepos", "onRefreshClick")
        viewState.showLoading()
        viewState.clearRepositoriesList()
        githubRepositoriesObserver = JwRepositoriesObserver()
        GetJWRepositoriesUseCase.execute(observer = githubRepositoriesObserver as JwRepositoriesObserver)
    }

    inner class JwRepositoriesObserver : DisposableObserver<GithubRepositoryModel>() {
        override fun onNext(githubRepository: GithubRepositoryModel) {
            this@RepositoriesPresenter.viewState.showRepoInList(githubRepository)
        }

        override fun onError(e: Throwable?) {
            this@RepositoriesPresenter.viewState.hideLoading()
            this@RepositoriesPresenter.viewState.showErrorMessage()
        }

        override fun onComplete() {
            this@RepositoriesPresenter.viewState.hideLoading()
        }

    }

}
