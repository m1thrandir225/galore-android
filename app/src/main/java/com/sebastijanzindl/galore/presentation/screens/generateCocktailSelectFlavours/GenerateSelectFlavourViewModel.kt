package com.sebastijanzindl.galore.presentation.screens.generateCocktailSelectFlavours

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.Flavour
import com.sebastijanzindl.galore.domain.usecase.GetAllFlavoursUseCase
import com.sebastijanzindl.galore.presentation.component.SnackbarMessage
import com.sebastijanzindl.galore.presentation.component.UserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateSelectFlavourViewModel @Inject constructor(
    private val  getAllFlavoursUseCase: GetAllFlavoursUseCase
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

    fun dismissToast() {
        _toastMessage.update { null }
    }
    private fun getFlavours() {
        viewModelScope.launch {
            try {
                _isLoading.value = true;
                val response = getAllFlavoursUseCase.execute(
                    GetAllFlavoursUseCase.Input()
                )

                if (response.result == null) throw Exception("There was a problem loading the required data.")

                _allFlavours.value = response.result

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