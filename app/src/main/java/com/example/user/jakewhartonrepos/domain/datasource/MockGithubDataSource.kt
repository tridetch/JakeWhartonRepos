package com.example.user.jakewhartonrepos.domain.datasource

import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import io.reactivex.Observable

/**
 * Created by User on 20.06.2017.
 */
class MockGithubDataSource : GithubRepositoriesSource {

    val whartonsRepos: ArrayList<GithubRepositoryModel> = ArrayList()

    init {
        whartonsRepos.add(GithubRepositoryModel("timber", "A logger with a small, extensible API which provides utility on top of Android's normal Log class.", 4217))
        whartonsRepos.add(GithubRepositoryModel("Kotterknife", "View injection library for Android.", 1278))
        whartonsRepos.add(GithubRepositoryModel("Reagent", "An experiment which rewrites RxJava-like types using polymorphism (and also in Kotlin).", 74))
    }

    override fun getGithubRepositories(): Observable<List<GithubRepositoryModel>> {
        return Observable.fromArray(whartonsRepos)
    }
}