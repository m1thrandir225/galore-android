package com.sebastijanzindl.galore.presentation.screens.favouriteFlavours

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.Flavour
import com.sebastijanzindl.galore.domain.usecase.AddFlavoursToFavouritesUseCase
import com.sebastijanzindl.galore.domain.usecase.GetAllFlavoursUseCase
import com.sebastijanzindl.galore.presentation.component.SnackbarMessage
import com.sebastijanzindl.galore.presentation.component.UserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.Auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteFlavoursScreenViewModel @Inject constructor(
    private val getAllFlavoursUseCase: GetAllFlavoursUseCase,
    private val addFlavoursToFavouritesUseCase: AddFlavoursToFavouritesUseCase,
    private val auth: Auth
) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false);
    val isLoading = _isLoading.asStateFlow();

    private val _allFlavours = MutableStateFlow<List<Flavour>>(emptyList());
    val allFlavours = _allFlavours.asStateFlow();

    private val _toastMessage = MutableStateFlow<SnackbarMessage?>(null)
    val toastMessage = _toastMessage.asStateFlow()


    init {
        getFlavours()
    }
    fun dismissToastMessage() {
        _toastMessage.update { null }
    }

    fun submitFlavours(flavourIds: List<String>, onSuccessCallback: () -> Unit) {
        viewModelScope.launch {
            try {
                _isLoading.value = true;
                val user = auth.currentUserOrNull()
                    ?: throw Exception("There was an error fetching your id.");

                val result = addFlavoursToFavouritesUseCase.execute(
                    AddFlavoursToFavouritesUseCase.Input(
                       userId =user.id,
                        flavoursIds = flavourIds
                    )
                )
                when(result) {
                    is AddFlavoursToFavouritesUseCase.Output.Success -> {
                        onSuccessCallback()
                    }
                    is AddFlavoursToFavouritesUseCase.Output.Failure -> {
                        throw Exception("There was an error during the request.")
                    }
                }

            } catch (e: Exception) {
                _toastMessage.value = SnackbarMessage.from(
                    duration = SnackbarDuration.Short,
                    userMessage = UserMessage.from(e.message.toString()),
                    actionLabelMessage = null,
                    onSnackbarResult = {},
                    withDismissAction = false
                )
            } finally {
                _isLoading.value = false;
            }
        }
    }
    private fun getFlavours() {
        viewModelScope.launch {
            try {
                _isLoading.value = true;
                val response = getAllFlavoursUseCase.execute(
                    GetAllFlavoursUseCase.Input()
                )

                if(response.result == null) throw Exception("There was a problem loading the required data.")

                _allFlavours.value = response.result

            } catch (e: Exception) {
                _toastMessage.value = SnackbarMessage.from(
                    duration = SnackbarDuration.Short,
                    userMessage = UserMessage.from(e.message.toString()),
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