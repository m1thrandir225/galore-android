package com.sebastijanzindl.galore.presentation.screens.cocktailDetails

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.domain.usecase.AddCocktailToFavouritesUseCase
import com.sebastijanzindl.galore.domain.usecase.GetSingleCocktailUseCase
import com.sebastijanzindl.galore.domain.usecase.RemoveCocktailFromFavouritesUseCase
import com.sebastijanzindl.galore.presentation.component.SnackbarMessage
import com.sebastijanzindl.galore.presentation.component.UserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.Auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailDetailsScreenViewModel  @Inject constructor(
    private val getSingleCocktailUseCase: GetSingleCocktailUseCase,
    private val addCocktailToFavouriteUseCase: AddCocktailToFavouritesUseCase,
    private val removeCocktailFromFavouritesUseCase: RemoveCocktailFromFavouritesUseCase,
    private val auth: Auth
): ViewModel(){
    private val _isLoading = MutableStateFlow<Boolean>(false);
    val isLoading = _isLoading.asStateFlow();

    private val _cocktail = MutableStateFlow<Cocktail?>(null)
    val cocktail = _cocktail.asStateFlow();

    private val _isFavourite = MutableStateFlow<Boolean>(true);
    val isFavourite = _isFavourite.asStateFlow();

    private val _toastMessage = MutableStateFlow<SnackbarMessage?>(null)
    val toastMessage = _toastMessage.asStateFlow()

    fun getCocktail(id: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                val result = getSingleCocktailUseCase.execute(
                    GetSingleCocktailUseCase.Input(id)
                )
                if(result.result == null) throw Exception("There was a problem fetching the cocktail.")
                _cocktail.value = result.result
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
                _isLoading.value = false
            }

        }
    }

    fun removeCocktailFromFavourites(cocktailId: String) {
        viewModelScope.launch {
            try {
                val user = auth.currentUserOrNull() ?: throw Exception("User not logged in.");

                val result = removeCocktailFromFavouritesUseCase.execute(
                    RemoveCocktailFromFavouritesUseCase.Input(cocktailId = cocktailId, userId =  user.id)
                )

                when(result) {
                    is RemoveCocktailFromFavouritesUseCase.Output.Success -> {
                        _isFavourite.value = false
                    }
                    is RemoveCocktailFromFavouritesUseCase.Output.Failure -> {
                        throw Exception("There was a problem adding the cocktail to favourites.")
                    }
                }
            } catch(e: Exception) {
                Log.e("Possible exception", e.message.toString())
                _toastMessage.value = SnackbarMessage.from(
                    duration = SnackbarDuration.Short,
                    userMessage = UserMessage.from("An error occurred."),
                    actionLabelMessage = null,
                    onSnackbarResult = {},
                    withDismissAction = false
                )
            }
        }
    }

    fun addCocktailToFavourites(cocktailId: String) {
        viewModelScope.launch {
            try {
                val user = auth.currentUserOrNull() ?: throw Exception("User not logged in.");

                val result = addCocktailToFavouriteUseCase.execute(
                    AddCocktailToFavouritesUseCase.Input(cocktailId = cocktailId, userId =  user.id)
                )

                when(result) {
                    is AddCocktailToFavouritesUseCase.Output.Success -> {
                        _isFavourite.value = true
                    }
                    is AddCocktailToFavouritesUseCase.Output.Failure -> {
                        throw Exception("There was a problem adding the cocktail to favourites.")
                    }
                }
            } catch(e: Exception) {
                Log.e("Possible exception", e.message.toString())
                _toastMessage.value = SnackbarMessage.from(
                    duration = SnackbarDuration.Short,
                    userMessage = UserMessage.from("An error occurred."),
                    actionLabelMessage = null,
                    onSnackbarResult = {},
                    withDismissAction = false
                )
            }
        }
    }
}