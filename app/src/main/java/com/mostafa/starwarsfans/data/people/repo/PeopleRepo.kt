package com.mostafa.starwarsfans.data.people.repo

import com.mostafa.starwarsfans.data.people.model.PeopleResponse

interface PeopleRepo {
    suspend fun getAllPeopleFromRemote(page:Int):PeopleResponse
}