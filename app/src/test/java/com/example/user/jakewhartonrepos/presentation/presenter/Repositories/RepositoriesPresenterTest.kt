package com.example.user.jakewhartonrepos.presentation.presenter.Repositories

import com.example.user.jakewhartonrepos.data.model.GithubRepositoryModel
import com.example.user.jakewhartonrepos.domain.interactor.GetJWRepositoriesUseCase
import com.example.user.jakewhartonrepos.domain.repository.GitDataRepositoriesImpl
import com.example.user.jakewhartonrepos.presentation.view.Repositories.RepositoriesView
import com.example.user.jakewhartonrepos.utils.TestExecutor
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class RepositoriesPresenterTest {

    lateinit var repositoriesPresenter: RepositoriesPresenter

    val repositoriesView: RepositoriesView = mock()
    val getJwRepositoriesUseCase: GetJWRepositoriesUseCase = mock()
    val gitDataRepositoriesImpl: GitDataRepositoriesImpl = mock()
    val githubRepositoriesObserver: RepositoriesPresenter.JwRepositoriesObserver = mock()

    @Before
    fun setUp() {
        repositoriesPresenter = RepositoriesPresenter(getJwRepositoriesUseCase)
        whenever(gitDataRepositoriesImpl.getGithubRepositories(any())).thenReturn(getStubObservable())
        getJwRepositoriesUseCase.githubDataRepository = gitDataRepositoriesImpl
        getJwRepositoriesUseCase.subscribeScheduler = Schedulers.from(TestExecutor())
        getJwRepositoriesUseCase.observeScheduler = Schedulers.from(TestExecutor())
        getJwRepositoriesUseCase.githubDataRepository = gitDataRepositoriesImpl
        repositoriesPresenter.attachView(repositoriesView)
    }

    @Test
    @Throws(Exception::class)
    fun onRefreshClick() {
        repositoriesPresenter.onRefreshClick()
        verify(repositoriesView).showLoading()
        verify(repositoriesView).clearRepositoriesList()
        verify(getJwRepositoriesUseCase).execute(any())
    }

    @Test
    @Throws(Exception::class)
    fun onDetach() {
        repositoriesPresenter.onAttach()
        repositoriesPresenter.onDetach()
    }

    fun getStubObservable(): Observable<List<GithubRepositoryModel>> {
        val whartonsRepos: ArrayList<GithubRepositoryModel> = ArrayList()
        whartonsRepos.add(GithubRepositoryModel("timber", "A logger with a small, extensible API which provides utility on top of Android's normal Log class.", 4217))
        whartonsRepos.add(GithubRepositoryModel("Kotterknife", "View injection library for Android.", 1278))
        whartonsRepos.add(GithubRepositoryModel("Reagent", "An experiment which rewrites RxJava-like types using polymorphism (and also in Kotlin).", 74))

        return Observable.fromArray(whartonsRepos)
    }
}