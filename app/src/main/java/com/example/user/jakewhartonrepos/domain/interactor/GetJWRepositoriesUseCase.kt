package com.example.user.jakewhartonrepos.domain.interactor

import com.example.user.jakewhartonrepos.domain.repository.GitDataRepository
import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import io.reactivex.Observer
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Use the case to retrieve the Jake Wharton repository collection, except those whose names begin with the letter "T" {@link User}.
 * */
open class GetJWRepositoriesUseCase @Inject constructor(var githubDataRepository: GitDataRepository,
                                                        val subscribeScheduler: Scheduler,
                                                        val observeScheduler: Scheduler) {
    fun execute(observer: Observer<GithubRepositoryModel>) {
        githubDataRepository.getGithubRepositories("JakeWharton")
                .flatMapIterable { it -> it }
                .filter { (name) -> !name.startsWith("T", true) }
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribeWith(observer)
    }
}