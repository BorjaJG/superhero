package com.example.superhero.feature.data.remote

import com.example.superhero.feature.domain.model.SuperHero
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroApiService {
    @GET("id/{id}.json")
    suspend fun getSuperHeroById(@Path("id") id: Int): SuperHero

    @GET("all.json")
    suspend fun getAllSuperHeroes(): List<SuperHero>
}
