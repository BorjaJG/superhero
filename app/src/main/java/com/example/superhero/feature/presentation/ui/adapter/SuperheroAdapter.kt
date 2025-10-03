package com.example.superhero.feature.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.superhero.R
import com.example.superhero.feature.domain.model.SuperHero


class SuperHeroAdapter : androidx.recyclerview.widget.ListAdapter<SuperHero, SuperHeroViewHolder>(SuperHeroDiffUtil()) {

    lateinit var onClick: (superHero: SuperHero) -> Unit

    fun setEvent(onClick: (superHero: SuperHero) -> Unit) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        return SuperHeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(getItem(position)) { superHero ->
            onClick(superHero)
        }
    }
}
