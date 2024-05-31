package com.sebastijanzindl.galore.presentation.screens.generatedCocktailDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.UserMadeCocktail
import com.sebastijanzindl.galore.domain.usecase.GetSingleGeneratedCocktailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneratedCocktailDetailsScreenViewModel @Inject constructor(
    private val getSingleGeneratedCocktailUseCase: GetSingleGeneratedCocktailUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false);
    val isLoading = _isLoading.asStateFlow();

    private val _cocktail = MutableStateFlow<UserMadeCocktail?>(null);
    val cocktail = _cocktail.asStateFlow();

    fun getCocktail(id: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true;
                val result =  getSingleGeneratedCocktailUseCase.execute(
                    GetSingleGeneratedCocktailUseCase.Input(id)
                )
                if(result.result == null) throw Exception("Error while fetching the cocktail.")

                _cocktail.value = result.result
            } catch (e: Exception) {
                //TODO: toast message toadd
            } finally {
                _isLoading.value = false;
            }
        }
    }
}