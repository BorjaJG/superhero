package com.example.superhero.feature.data.local


import android.content.Context
import com.example.superhero.R
import com.example.superhero.feature.domain.model.SuperHero
import com.google.gson.Gson


class SuperheroXmlLocalDataSource(private val context: Context) {

    private val gson = Gson()

    private val sharedPreferences =
        context.getSharedPreferences(
            context.getString(R.string.superheroes_file_xml),
            Context.MODE_PRIVATE
        )


    fun saveAll(superheroes: List<SuperHero>) {
        val editor = sharedPreferences.edit()
        superheroes.forEach { superhero ->
            editor.putString(superhero.id, gson.toJson(superhero))
        }
        editor.apply()
    }

    fun getSuperheroes(): List<SuperHero> {
        val superheroes = mutableListOf<SuperHero>()
        val mapSuperheroes = sharedPreferences.all
        mapSuperheroes.values.forEach { jsonSuperhero ->
            val superhero = gson.fromJson(jsonSuperhero as String, SuperHero::class.java)
            superheroes.add(superhero)
        }
        return superheroes
    }


}