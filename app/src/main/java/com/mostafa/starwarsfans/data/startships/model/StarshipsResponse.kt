package com.mostafa.starwarsfans.data.startships.model

import com.google.gson.annotations.SerializedName

data class StarshipsResponse(
    val count: Int,
    val next: String,
    val previous: Any,

    @SerializedName("results")
    val starships: List<Starship>
)