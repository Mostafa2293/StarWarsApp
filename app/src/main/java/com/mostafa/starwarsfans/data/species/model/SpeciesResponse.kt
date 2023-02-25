package com.mostafa.starwarsfans.data.species.model

import com.google.gson.annotations.SerializedName

data class SpeciesResponse(
    val count: Int,
    val next: String,
    val previous: Any,

    @SerializedName("results")
    val species: List<Species>
)