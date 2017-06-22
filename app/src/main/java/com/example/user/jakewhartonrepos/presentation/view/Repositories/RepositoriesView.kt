package com.example.user.jakewhartonrepos.presentation.view.Repositories

import com.arellomobile.mvp.MvpView
import com.example.user.jakewhartonrepos.model.GithubRepositoryModel

interface RepositoriesView : MvpView {
    fun showRepoInList(gitHubRepo: GithubRepositoryModel)
    fun refresh()
    fun clearRepositoriesList()
    fun showErrorMessage()
    fun hideLoading()
    fun clearRepoList()
    fun showLoading()
}
