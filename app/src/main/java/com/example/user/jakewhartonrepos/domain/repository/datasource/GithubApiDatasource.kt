package com.example.user.jakewhartonrepos.domain.repository.datasource

import com.example.user.jakewhartonrepos.data.model.GithubRepositoryModel
import com.example.user.jakewhartonrepos.data.net.GithubService
import io.reactivex.Observable
import javax.inject.Inject

open class GithubApiDatasource @Inject constructor(val githubService: GithubService) : GithubRepositoriesSource {
    override fun getGithubRepositories(username: String): Observable<List<GithubRepositoryModel>> {
        return githubService.repositories(username)
    }
}