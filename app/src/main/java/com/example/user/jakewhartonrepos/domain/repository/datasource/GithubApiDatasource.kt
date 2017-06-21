package com.example.user.jakewhartonrepos.domain.repository.datasource

import com.example.user.jakewhartonrepos.data.net.GithubService
import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import io.reactivex.Observable
import javax.inject.Inject

class GithubApiDatasource @Inject constructor(val githubService: GithubService) : GithubRepositoriesSource {
    override fun getGithubRepositories(): Observable<List<GithubRepositoryModel>> {
        return githubService.repositories()
    }
}