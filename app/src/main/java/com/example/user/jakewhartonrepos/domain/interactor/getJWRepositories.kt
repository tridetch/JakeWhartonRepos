package com.example.user.jakewhartonrepos.domain.interactor

import com.example.user.jakewhartonrepos.domain.repository.GitDataRepository
import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Use the case to retrieve the Jake Wharton repository collection, except those whose names begin with the letter "T" {@link User}.
 * */
class getJWRepositories @Inject constructor(val githubDataRepository: GitDataRepository) {
    fun execute(observer: DisposableObserver<GithubRepositoryModel>) {
        githubDataRepository.getGithubRepositories()
                .flatMapIterable { it -> it }
                .filter { (name) -> !name.startsWith("T", true) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)
    }
}