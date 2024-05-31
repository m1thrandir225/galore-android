package com.sebastijanzindl.galore.presentation.screens.generateCocktailSelectCocktails

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.domain.usecase.GetCocktailsBySearchUseCase
import com.sebastijanzindl.galore.domain.usecase.GetPopularCocktailsUseCase
import com.sebastijanzindl.galore.presentation.component.SnackbarMessage
import com.sebastijanzindl.galore.presentation.component.UserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateSelectCocktailsViewModel @Inject constructor(
    private val getPopularCocktailsUseCase: GetPopularCocktailsUseCase,
    private val getCocktailsBySearchUseCase: GetCocktailsBySearchUseCase,
) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false);
    val isLoading = _isLoading.asStateFlow();

    private val _cocktails = MutableStateFlow<List<Cocktail>>(emptyList())
    val cocktails = _cocktails.asStateFlow();

    private val _toastMessage = MutableStateFlow<SnackbarMessage?>(null)
    val toastMessage = _toastMessage.asStateFlow()

    private val _hasSearchResults = MutableStateFlow<Boolean>(false);
    val hasSearchResult = _hasSearchResults.asStateFlow();

    fun dismissToast() {
        _toastMessage.update { null }
    }

    init {
        getInitialCocktails();
    }
    fun getInitialCocktails() {
        viewModelScope.launch {
            try {
                _isLoading.value = true;
                val result = getPopularCocktailsUseCase.execute(
                    GetPopularCocktailsUseCase.Input()
                )
                if(result.result == null) throw Exception("There was a problem fetching the required data.")

                _cocktails.value = result.result
                _hasSearchResults.value = false
            } catch(e: Exception) {
                Log.e("Possible exception", e.message.toString())
                _toastMessage.value = SnackbarMessage.from(
                    duration = SnackbarDuration.Short,
                    userMessage = UserMessage.from("An error occurred."),
                    actionLabelMessage = null,
                    onSnackbarResult = {},
                    withDismissAction = false
                )
            } finally {
                _isLoading.value = false;
            }

        }
    }

    fun getSearchCocktails(searchString: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true;
                val result = getCocktailsBySearchUseCase.execute(
                    GetCocktailsBySearchUseCase.Input(searchString)
                )
                if (result.result == null) throw Exception("There was a problem fetching the required data.");
                _cocktails.value = result.result
                _hasSearchResults.value = true;
            } catch (e: Exception) {
                Log.e("Possible exception", e.message.toString())
                _toastMessage.value = SnackbarMessage.from(
                    duration = SnackbarDuration.Short,
                    userMessage = UserMessage.from("An error occurred."),
                    actionLabelMessage = null,
                    onSnackbarResult = {},
                    withDismissAction = false
                )
            } finally {
                _isLoading.value = false;
            }
        }
    }
}