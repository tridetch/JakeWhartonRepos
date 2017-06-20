package com.example.user.jakewhartonrepos.presentation.presenter.Repositories

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.user.jakewhartonrepos.domain.GitDataRepository
import com.example.user.jakewhartonrepos.presentation.view.Repositories.RepositoriesView

@InjectViewState
class RepositoriesPresenter(val githubDataRepository: GitDataRepository) : MvpPresenter<RepositoriesView>() {

    fun onAttach() {
        githubDataRepository.getGithubRepositories().filter { r-> !r.name.startsWith("T") }.subscribe(
                {gitHubRepo -> viewState.showRepoInList(gitHubRepo)},
                {err -> viewState.showErrorMessage()},
                {-> viewState.showCompleteMessage()}
        )
    }

}
