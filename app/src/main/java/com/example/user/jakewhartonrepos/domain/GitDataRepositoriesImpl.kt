package com.example.user.jakewhartonrepos.domain

import com.example.user.jakewhartonrepos.domain.datasource.GithubRepositoriesSource
import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import io.reactivex.Observable

/**
 * Created by User on 20.06.2017.
 */
class GitDataRepositoriesImpl(val githubApiDataSource: GithubRepositoriesSource) : GitDataRepository {

    override fun getGithubRepositories(): Observable<GithubRepositoryModel> {
        return githubApiDataSource.getGithubRepositories()
    }
}