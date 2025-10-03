package com.example.superhero.feature.presentation.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.superhero.databinding.ItemSuperheroBinding
import com.example.superhero.feature.domain.model.SuperHero

class SuperHeroViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHero: SuperHero, onClick: (SuperHero) -> Unit) {
        binding.apply {
            ivSuperhero.load(superHero.images.lg) // Coil
            tvSuperheroName.text = superHero.name
            tvIntelligence.text = "Intelligence: ${superHero.powerstats.intelligence}"
            tvStrength.text = "Strength: ${superHero.powerstats.strength}"
            tvSpeed.text = "Speed: ${superHero.powerstats.speed}"
        }

        view.setOnClickListener {
            onClick(superHero)
        }
    }
}
