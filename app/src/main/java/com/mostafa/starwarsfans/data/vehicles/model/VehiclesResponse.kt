package com.mostafa.starwarsfans.data.vehicles.model

import com.google.gson.annotations.SerializedName

data class VehiclesResponse(
    val count: Int,
    val next: String,
    val previous: Any,

    @SerializedName("results")
    val vehicles: List<Vehicle>
)