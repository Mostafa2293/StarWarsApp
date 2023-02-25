package com.mostafa.starwarsfans.data.films.model

import com.google.gson.annotations.SerializedName

data class FilmsResponse(
    val count: Int,
    val next: Any,
    val previous: Any,

    @SerializedName("results")
    val films: List<Films>
)