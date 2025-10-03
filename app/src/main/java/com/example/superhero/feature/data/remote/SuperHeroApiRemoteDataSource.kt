package com.example.superhero.feature.data.remote

import com.example.superhero.feature.domain.model.SuperHero
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroApiRemoteDataSource {

    private val api: SuperHeroApiService by lazy {
        Retrofit.Builder().baseUrl("https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(SuperHeroApiService::class.java)
    }

    suspend fun getAllSuperHero(): List<SuperHero> {
        return api.getAllSuperHeroes()
    }

}
