package com.example.superhero.feature.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superhero.feature.domain.model.SuperHero
import com.example.superhero.feature.domain.usecase.GetAllUseCase
import com.example.superhero.core.presentation.views.ErrorAppView.ErrorAppUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SuperHeroAboutViewModel(
    private val getSuperHeroesUseCase: GetAllUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun viewSuperHeroes() {
        _uiState.value = UiState(isLoading = true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val superHeroesList: List<SuperHero> = getSuperHeroesUseCase()
                _uiState.postValue(
                    UiState(
                        isLoading = false,
                        superHeroes = superHeroesList,
                        errorMessage = null
                    )
                )
            } catch (e: Exception) {
                _uiState.postValue(
                    UiState(
                        isLoading = false,
                        superHeroes = emptyList(),
                        errorMessage = ErrorAppUI(
                            message = e.message ?: "Unknown error",
                            onRetry = { viewSuperHeroes() }
                        )
                    )
                )
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorMessage: ErrorAppUI? = null,
        val superHeroes: List<SuperHero>? = emptyList()
    )
}
