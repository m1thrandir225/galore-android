package com.sebastijanzindl.galore.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.GenerateCocktailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.Auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateCocktailViewModel @Inject constructor(
    private val generateCocktailUseCase: GenerateCocktailUseCase,
    private val auth: Auth
) : ViewModel() {
    private val _isGenerating = MutableStateFlow<Boolean>(false);
    val isGenerating = _isGenerating.asStateFlow();

    private val _userFavouriteFlavours = MutableStateFlow<List<String>>(emptyList())
    val userFavouriteFlavours = _userFavouriteFlavours.asStateFlow();

    private val _userFavouriteCocktails = MutableStateFlow<List<String>>(emptyList())
    val userFavouriteCocktails = _userFavouriteCocktails.asStateFlow()

    private val _generatedCocktailId = MutableStateFlow<String?>(null)
    val generatedCocktailId = _generatedCocktailId.asStateFlow();
    fun addLikedFlavours(likedFlavours: List<String>) {
        _userFavouriteFlavours.value = likedFlavours
    }

    fun addLikedCocktails(likedCocktails: List<String>) {
        _userFavouriteCocktails.value = likedCocktails
    }

    fun generateCocktail() {
        viewModelScope.launch {
            try {
                println("hello world")
                _isGenerating.value = true;
                val session = auth.currentSessionOrNull() ?: throw Exception("User not logged in.");

                val prompt = "The user has selected: ${userFavouriteFlavours.value.joinToString()}. And as reference cocktails he selected: ${userFavouriteCocktails.value.joinToString()}"
                val response = generateCocktailUseCase.execute(
                    GenerateCocktailUseCase.Input(
                        prompt = prompt,
                        token = session.accessToken
                    )
                )
                _generatedCocktailId.value = response.cocktailId
            } catch (e: Exception) {
                //TODO
            } finally {
                _isGenerating.value = false;
            }
        }
    }
}