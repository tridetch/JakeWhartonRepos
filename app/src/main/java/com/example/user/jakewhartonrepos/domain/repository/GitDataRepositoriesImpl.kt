package com.example.user.jakewhartonrepos.domain.repository

import com.example.user.jakewhartonrepos.data.model.GithubRepositoryModel
import com.example.user.jakewhartonrepos.domain.repository.datasource.GithubRepositoriesSource
import io.reactivex.Observable

/**
 * Created by User on 20.06.2017.
 */
open class GitDataRepositoriesImpl(var githubApiDataSource: GithubRepositoriesSource) : GitDataRepository {

    override fun getGithubRepositories(username: String): Observable<List<GithubRepositoryModel>> {
        return githubApiDataSource.getGithubRepositories(username)
    }
}