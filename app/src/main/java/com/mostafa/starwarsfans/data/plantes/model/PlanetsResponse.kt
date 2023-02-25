package com.mostafa.starwarsfans.data.plantes.model

import com.google.gson.annotations.SerializedName

data class PlanetsResponse(
    val count: Int,
    val next: String,
    val previous: Any,

    @SerializedName("results")
    val planets: List<Planet>
)