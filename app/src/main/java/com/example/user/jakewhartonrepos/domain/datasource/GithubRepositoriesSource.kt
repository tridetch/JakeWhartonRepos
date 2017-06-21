package com.example.user.jakewhartonrepos.domain.datasource

import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import io.reactivex.Observable

/**
 * Created by User on 20.06.2017.
 */
interface GithubRepositoriesSource {
    fun getGithubRepositories(): Observable<List<GithubRepositoryModel>>
}