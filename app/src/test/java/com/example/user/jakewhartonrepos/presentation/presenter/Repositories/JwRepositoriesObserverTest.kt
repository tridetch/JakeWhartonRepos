package com.example.user.jakewhartonrepos.presentation.presenter.Repositories

import com.example.user.jakewhartonrepos.data.model.GithubRepositoryModel
import com.example.user.jakewhartonrepos.domain.interactor.GetJWRepositoriesUseCase
import com.example.user.jakewhartonrepos.presentation.view.Repositories.RepositoriesView
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

class JwRepositoriesObserverTest {

    val getJwRepositoriesUseCase: GetJWRepositoriesUseCase = mock()
    val repositoriesView: RepositoriesView = mock()
    val repositoriesPresenter: RepositoriesPresenter = RepositoriesPresenter(getJwRepositoriesUseCase)
    val githubRepositoriesObserver: RepositoriesPresenter.JwRepositoriesObserver = repositoriesPresenter.JwRepositoriesObserver()

    val githubRepositoryModel = GithubRepositoryModel("name","description", 42)

    @Before
    fun setUp() {
        repositoriesPresenter.attachView(repositoriesView)
    }

    @Test
    fun onNext() {
        githubRepositoriesObserver.onNext(githubRepositoryModel)
        verify(repositoriesView).showRepoInList(githubRepositoryModel)
    }

    @Test
    fun onError() {
        githubRepositoriesObserver.onError(Throwable())
        verify(repositoriesView).hideLoading()
        verify(repositoriesView).showErrorMessage()
    }

    @Test
    fun onComplete() {
        githubRepositoriesObserver.onComplete()
        verify(repositoriesView).hideLoading()
    }

}