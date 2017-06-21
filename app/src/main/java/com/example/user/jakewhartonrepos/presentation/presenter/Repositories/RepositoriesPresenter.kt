package com.example.user.jakewhartonrepos.presentation.presenter.Repositories

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.user.jakewhartonrepos.domain.GitDataRepository
import com.example.user.jakewhartonrepos.presentation.view.Repositories.RepositoriesView

@InjectViewState
class RepositoriesPresenter(val githubDataRepository: GitDataRepository) : MvpPresenter<RepositoriesView>() {

    fun onAttach() {
        githubDataRepository.getGithubRepositories()
                .flatMapIterable { it -> it }
                .filter { (name) -> !name.startsWith("T", true) }
                .subscribe({ githubRepo -> viewState.showRepoInList(githubRepo) },
                        { err -> viewState.showErrorMessage() },
                        { -> viewState.showCompleteMessage() })
    }

}
