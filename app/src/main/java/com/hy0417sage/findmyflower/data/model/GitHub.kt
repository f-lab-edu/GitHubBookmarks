package com.hy0417sage.findmyflower.data.model

import com.google.gson.annotations.SerializedName

data class GitHub (
    @SerializedName("total_count") val totalCount : Int,
    @SerializedName("items") val items : List<Item>) {
    data class Item(
        @SerializedName("login") val userId: String,
        @SerializedName("avatar_url") val userProfileImg: String,
        @SerializedName("html_url") val userGitHubPage: String
    )
}
