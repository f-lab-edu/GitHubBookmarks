package com.hy0417sage.findmyflower.github

import com.google.gson.annotations.SerializedName

data class GitHubData (
    @SerializedName("login") val userId : String,
    @SerializedName("avatar_url") val userGitHubUrl : String
)
