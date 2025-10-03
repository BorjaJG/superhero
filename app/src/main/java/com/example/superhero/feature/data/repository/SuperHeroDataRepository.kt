package com.example.superhero.feature.data.repository


import com.example.superhero.feature.data.local.SuperheroXmlLocalDataSource
import com.example.superhero.feature.data.remote.SuperHeroApiRemoteDataSource
import com.example.superhero.feature.domain.model.SuperHero
import com.example.superhero.feature.domain.repository.SuperHeroRepository

class SuperHeroDataRepository(
    private val superHeroXmlLocalDataSource: SuperheroXmlLocalDataSource,
    private val superHeroApiRemoteDataSource: SuperHeroApiRemoteDataSource
) : SuperHeroRepository {

    override suspend fun getAllSuperHero(): List<SuperHero> {
        val superheroesFromLocal = superHeroXmlLocalDataSource.getSuperheroes()
        if (superheroesFromLocal.isEmpty()) {
            val superheroesFromRemote = superHeroApiRemoteDataSource.getAllSuperHero()
            superHeroXmlLocalDataSource.saveAll(superheroesFromRemote)
            return superheroesFromRemote
        } else {
            return superheroesFromLocal
        }
    }


}