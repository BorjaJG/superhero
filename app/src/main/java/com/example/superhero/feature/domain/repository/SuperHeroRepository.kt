package com.example.superhero.feature.domain.repository

import com.example.superhero.feature.domain.model.SuperHero

interface SuperHeroRepository {
    suspend fun getAllSuperHero(): List<SuperHero>
}