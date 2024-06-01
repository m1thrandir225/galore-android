package com.sebastijanzindl.galore.presentation.screens.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.domain.models.UserMadeCocktail
import com.sebastijanzindl.galore.domain.usecase.GetAllUserMadeCocktails
import com.sebastijanzindl.galore.domain.usecase.GetUserLikedCocktailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.Auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryScreenViewModel @Inject constructor(
    private val authRepository: Auth,
    private val userMadeCocktailsUseCase: GetAllUserMadeCocktails,
    private val userLikedCocktailsUseCase: GetUserLikedCocktailsUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(false);
    val isLoading = _isLoading.asStateFlow();

    private val  _isRefreshing = MutableStateFlow<Boolean>(false);
    val isRefreshing = _isLoading.asStateFlow();

    private val _userMadeCocktails = MutableStateFlow<List<UserMadeCocktail>>(emptyList())
    val userMadeCocktails = _userMadeCocktails.asStateFlow();

    private val  _userLikedCocktails= MutableStateFlow<List<Cocktail>>(emptyList())
    val userLikedCocktails = _userLikedCocktails.asStateFlow();

    init {
        getCocktails();
    }

    fun refreshData() {
        try {
            _isRefreshing.value = true;
            getCocktails();
        } finally {
            _isRefreshing.value = false
        }
    }

    private fun getCocktails() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                authRepository.awaitInitialization();
                val user =
                    authRepository.currentUserOrNull() ?: throw Exception("User not logged in.");

                val cocktails = userMadeCocktailsUseCase.execute(
                    GetAllUserMadeCocktails.Input(
                        userId =  user.id
                    )
                )
                val likedCocktails = userLikedCocktailsUseCase.execute(
                    GetUserLikedCocktailsUseCase.Input()
                )

                _userMadeCocktails.value = cocktails.result
                _userLikedCocktails.value = likedCocktails.result

            } catch (e: Exception) {

            } finally {
                _isLoading.value = false;
            }
        }
    }

}