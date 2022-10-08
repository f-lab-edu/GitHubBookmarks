package com.hy0417sage.findmyflower.github

import com.google.gson.annotations.SerializedName

data class GitHubData (
    @SerializedName("total_count") val total_count : Int,
    @SerializedName("incomplete_results") val incomplete_results : Boolean,
    @SerializedName("items") val items : Items) {
        data class Items(
            @SerializedName("login") val login: String,
            @SerializedName("avatar_url") val avatar_url: String
        )
    }
