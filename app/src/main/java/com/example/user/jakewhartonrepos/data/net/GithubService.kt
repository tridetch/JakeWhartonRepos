package com.example.user.jakewhartonrepos.data.net

import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import io.reactivex.Observable
import retrofit2.http.GET

interface GithubService {
    @GET("/users/JakeWharton/repos")
    fun repositories(): Observable<List<GithubRepositoryModel>>
}