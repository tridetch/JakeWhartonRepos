package com.example.user.jakewhartonrepos

import com.example.user.jakewhartonrepos.domain.interactor.GetJWRepositoriesUseCase
import com.example.user.jakewhartonrepos.domain.repository.GitDataRepositoriesImpl
import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import com.example.user.jakewhartonrepos.presentation.presenter.Repositories.RepositoriesPresenter
import com.example.user.jakewhartonrepos.presentation.view.Repositories.RepositoriesView
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Matchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class RepositoriesPresenterTest {

    lateinit var repositoriesPresenter: RepositoriesPresenter

    @Mock
    lateinit var repositoriesView: RepositoriesView
    @Mock
    lateinit var getJwRepositoriesUseCase: GetJWRepositoriesUseCase
    @Mock
    lateinit var gitDataRepositoriesImpl: GitDataRepositoriesImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repositoriesPresenter = RepositoriesPresenter(getJwRepositoriesUseCase)
        getJwRepositoriesUseCase.githubDataRepository = gitDataRepositoriesImpl
        Mockito.`when`(gitDataRepositoriesImpl.getGithubRepositories(Matchers.anyString())).thenReturn(getStubObservable())
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

    fun getStubObservable(): Observable<List<GithubRepositoryModel>> {
        val whartonsRepos: ArrayList<GithubRepositoryModel> = ArrayList()
        whartonsRepos.add(GithubRepositoryModel("timber", "A logger with a small, extensible API which provides utility on top of Android's normal Log class.", 4217))
        whartonsRepos.add(GithubRepositoryModel("Kotterknife", "View injection library for Android.", 1278))
        whartonsRepos.add(GithubRepositoryModel("Reagent", "An experiment which rewrites RxJava-like types using polymorphism (and also in Kotlin).", 74))

        return Observable.fromArray(whartonsRepos)
    }
}