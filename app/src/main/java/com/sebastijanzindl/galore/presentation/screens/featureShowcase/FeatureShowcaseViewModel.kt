package com.sebastijanzindl.galore.presentation.screens.featureShowcase

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.GetUserFlavoursUseCase
import com.sebastijanzindl.galore.presentation.component.SnackbarMessage
import com.sebastijanzindl.galore.presentation.component.UserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.Auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeatureShowcaseViewModel @Inject constructor(
    private val getUserFlavoursUseCase: GetUserFlavoursUseCase,
    private val auth: Auth
) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isFirstTime = MutableStateFlow<Boolean?>(null)
    val isFirstTime = _isFirstTime.asStateFlow();

    private val _toastMessage = MutableStateFlow<SnackbarMessage?>(null)
    val toastMessage = _toastMessage.asStateFlow()

    init {
        getUserFlavours()
    }
    private fun getUserFlavours() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val user = auth.currentUserOrNull() ?: throw Exception("User not logged in.");
                val result = getUserFlavoursUseCase.execute(
                    GetUserFlavoursUseCase.Input(user.id)
                )
                if(result.result == null) throw Exception("There was a problem loading the data.")

                _isFirstTime.value = result.result.isEmpty()

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
}