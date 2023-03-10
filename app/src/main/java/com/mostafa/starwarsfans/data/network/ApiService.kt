package com.mostafa.starwarsfans.data.network

import com.mostafa.starwarsfans.data.films.model.FilmsResponse
import com.mostafa.starwarsfans.data.people.model.PeopleResponse
import com.mostafa.starwarsfans.data.plantes.model.PlanetsResponse
import com.mostafa.starwarsfans.data.species.model.SpeciesResponse
import com.mostafa.starwarsfans.data.startships.model.StarshipsResponse
import com.mostafa.starwarsfans.data.vehicles.model.VehiclesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ALL_PEOPLE)
    suspend fun getPeople(@Query("page") page:Int,@Query("search") search:String =""):PeopleResponse

    @GET(ALL_FILMS)
    suspend fun getFilms(@Query("page") page:Int,@Query("search") search:String =""):FilmsResponse

    @GET(ALL_PLANETS)
    suspend fun getPlanets(@Query("page") page:Int,@Query("search") search:String =""):PlanetsResponse

    @GET(ALL_SPECIES)
    suspend fun getSpecies(@Query("page") page:Int,@Query("search") search:String =""):SpeciesResponse

    @GET(ALL_STARSHIPS)
    suspend fun getStarships(@Query("page") page:Int,@Query("search") search:String =""):StarshipsResponse

    @GET(ALL_VEHICLES)
    suspend fun getVehicles(@Query("page") page:Int,@Query("search") search:String =""):VehiclesResponse

    companion object {
        //Get all people
        const val ALL_PEOPLE = "people/"
        //Get all films
        const val ALL_FILMS = "films/"
        //Get all planets
        const val ALL_PLANETS = "planets/"
        //Get all species
        const val ALL_SPECIES = "species/"
        //Get all starships
        const val ALL_STARSHIPS = "starships/"
        //Get all vehicles
        const val ALL_VEHICLES = "vehicles/"

    }
}