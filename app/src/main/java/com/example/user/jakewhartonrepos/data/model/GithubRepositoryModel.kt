package com.example.user.jakewhartonrepos.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by User on 20.06.2017.
 */
data class GithubRepositoryModel(@SerializedName("name") val name: String,
                                 @SerializedName("description") val description: String,
                                 @SerializedName("stargazers_count") val stars: Int) {

}