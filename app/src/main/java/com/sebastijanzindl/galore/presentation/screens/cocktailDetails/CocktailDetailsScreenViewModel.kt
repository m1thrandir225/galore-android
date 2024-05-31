package com.sebastijanzindl.galore.presentation.screens.cocktailDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.domain.usecase.GetSingleCocktailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailDetailsScreenViewModel  @Inject constructor(
    private val getSingleCocktailUseCase: GetSingleCocktailUseCase
): ViewModel(){
    private val _isLoading = MutableStateFlow<Boolean>(false);
    val isLoading = _isLoading.asStateFlow();

    private val _cocktail = MutableStateFlow<Cocktail?>(null)
    val cocktail = _cocktail.asStateFlow();

    fun getCocktail(id: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                val result = getSingleCocktailUseCase.execute(
                    GetSingleCocktailUseCase.Input(id)
                )
                Log.i("Cocktail Detail Screen", result.toString())
                if(result.result == null) throw Exception("There was a problem fetching the cocktial.")
                _cocktail.value = result.result
            } catch (e: Exception) {
                //TODO: toast message implement
                Log.e("Cocktail Details Screen", e.toString())
            } finally {
                _isLoading.value = false
            }

        }
    }
}