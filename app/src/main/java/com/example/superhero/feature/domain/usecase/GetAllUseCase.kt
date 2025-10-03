package com.example.superhero.feature.domain.usecase

import com.example.superhero.feature.domain.model.SuperHero
import com.example.superhero.feature.domain.repository.SuperHeroRepository

class GetAllUseCase (private  val  superHeroRepository: SuperHeroRepository) {
    suspend operator fun invoke (): List<SuperHero> {
        return  superHeroRepository.getAllSuperHero()
    }
}