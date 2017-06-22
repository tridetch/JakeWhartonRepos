package com.example.user.jakewhartonrepos.data.net

import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos?per_page=100")
    fun repositories(@Path("user") username: String): Observable<List<GithubRepositoryModel>>
}