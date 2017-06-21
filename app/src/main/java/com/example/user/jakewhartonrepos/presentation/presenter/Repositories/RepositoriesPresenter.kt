package com.example.user.jakewhartonrepos.presentation.presenter.Repositories

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.user.jakewhartonrepos.domain.repository.GitDataRepository
import com.example.user.jakewhartonrepos.presentation.view.Repositories.RepositoriesView
import io.reactivex.disposables.Disposable

@InjectViewState
class RepositoriesPresenter(val githubDataRepository: GitDataRepository) : MvpPresenter<RepositoriesView>() {

    var githubRepositoriesObserver: Disposable? = null

    fun onAttach() {
//        viewState.clearRepoList()
        //TODO("later") // observe on backgroundThread, subscribe on main thread
        if (githubRepositoriesObserver == null) {
            Log.d("JakeWhartonRepos", "subscribe to observable")
            githubRepositoriesObserver = githubDataRepository.getGithubRepositories()
                    .flatMapIterable { it -> it }
                    .filter { (name) -> !name.startsWith("T", true) }
                    .subscribe({ githubRepo -> viewState.showRepoInList(githubRepo) },
                            { err -> viewState.showErrorMessage() },
                            { -> viewState.showCompleteMessage() })
        }
    }

    fun onDetach() {
        Log.d("JakeWhartonRepos", "unsubscribe from observable")
        githubRepositoriesObserver?.dispose()
    }
}
