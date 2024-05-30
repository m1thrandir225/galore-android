package com.sebastijanzindl.galore.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GenerateCocktailViewModel @Inject constructor() : ViewModel() {
    private val _userFavouriteFlavours = MutableStateFlow<List<String>>(emptyList())
    val userFavouriteFlavours = _userFavouriteFlavours.asStateFlow();

    private val _userFavouriteCocktails = MutableStateFlow<List<String>>(emptyList())
    val userFavouriteCocktails = _userFavouriteCocktails.asStateFlow()
    fun addLikedFlavours(likedFlavours: List<String>) {
        _userFavouriteFlavours.value = likedFlavours
    }

    fun addLikedCocktails(likedCocktails: List<String>) {
        _userFavouriteCocktails.value = likedCocktails
    }
}