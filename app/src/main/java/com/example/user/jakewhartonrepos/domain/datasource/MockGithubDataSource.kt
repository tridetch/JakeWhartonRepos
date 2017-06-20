package com.example.user.jakewhartonrepos.domain.datasource

import com.example.user.jakewhartonrepos.domain.GitDataRepository
import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import io.reactivex.Observable

/**
 * Created by User on 20.06.2017.
 */
class MockGithubDataSource: GithubRepositoriesSource {

    val whartonsRepos: ArrayList<GithubRepositoryModel> = ArrayList()
    init {
        whartonsRepos.add(GithubRepositoryModel("timber", "Jake Wharton", 4217))
        whartonsRepos.add(GithubRepositoryModel("kotterknife", "Jake Wharton", 1278))
        whartonsRepos.add(GithubRepositoryModel("Reagent", "Jake Wharton", 74))
    }

    override fun getGithubRepositories(): Observable<GithubRepositoryModel> {
        return Observable.create { emitter ->
            for (repository in whartonsRepos) {
                emitter.onNext(repository)
            }
            emitter.onComplete()
        }
    }
}