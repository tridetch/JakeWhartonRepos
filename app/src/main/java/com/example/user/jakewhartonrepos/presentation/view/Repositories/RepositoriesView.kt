package com.example.user.jakewhartonrepos.presentation.view.Repositories

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.user.jakewhartonrepos.model.GithubRepositoryModel

interface RepositoriesView : MvpView {
    fun showRepoInList(gitHubRepo: GithubRepositoryModel)
    fun showErrorMessage()
    fun hideLoading()
    fun clearRepoList()
    fun showLoading()
}
