package com.mostafa.starwarsfans.data.network

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("count")
    val count: Int? = null

    @SerializedName("next")
    val next: String? = null

    @SerializedName("previous")
    val previous: String? = null

}