package com.example.user.jakewhartonrepos.domain.repository.datasource

import com.example.user.jakewhartonrepos.data.model.GithubRepositoryModel
import io.reactivex.Observable

/**
 * Created by User on 20.06.2017.
 */
interface GithubRepositoriesSource {
    fun getGithubRepositories(username: String): Observable<List<GithubRepositoryModel>>
}