package com.example.user.jakewhartonrepos.domain.repository

import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import io.reactivex.Observable

/**
 * Interface that represents a Repository for getting {@link GithubRepositoryModel} related data.
 */
interface GitDataRepository {
    /**
     * Get an {@link Observable} which will emit a List of {@link GithubRepositoryModel}.
     */
    fun getGithubRepositories(username: String): Observable<List<GithubRepositoryModel>>
}