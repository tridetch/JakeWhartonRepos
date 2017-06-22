package com.example.user.jakewhartonrepos.domain.interactor

import com.example.user.jakewhartonrepos.domain.repository.GitDataRepository
import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Executor

class GetJWRepositoriesUseCaseTest {

    lateinit var getJwRepositoriesUseCase: GetJWRepositoriesUseCase

    @Before
    fun setUp() {
        getJwRepositoriesUseCase = GetJWRepositoriesUseCase(StubGitDataRepository(), Schedulers.from(MainThreadExecutor()), Schedulers.from(MainThreadExecutor()))
    }

    @Test
    fun execute() {
        val testSubscriber: TestObserver<GithubRepositoryModel> = TestObserver()
        getJwRepositoriesUseCase.execute(testSubscriber)
        testSubscriber.assertNever { (name)-> name.startsWith("T",true) }
    }

    class StubGitDataRepository : GitDataRepository {
        val whartonsRepos: ArrayList<GithubRepositoryModel> = ArrayList()

        init {
            whartonsRepos.add(GithubRepositoryModel("timber", "A logger with a small, extensible API which provides utility on top of Android's normal Log class.", 4217))
            whartonsRepos.add(GithubRepositoryModel("Kotterknife", "View injection library for Android.", 1278))
            whartonsRepos.add(GithubRepositoryModel("Reagent", "An experiment which rewrites RxJava-like types using polymorphism (and also in Kotlin).", 74))
        }

        override fun getGithubRepositories(username: String): Observable<List<GithubRepositoryModel>> {
            return Observable.fromArray(whartonsRepos)
        }
    }

    private class MainThreadExecutor : Executor {
        override fun execute(runnable: Runnable) {
            runnable.run()
        }
    }

}