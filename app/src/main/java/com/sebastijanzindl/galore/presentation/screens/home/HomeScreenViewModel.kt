package com.sebastijanzindl.galore.presentation.screens.home

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.Section
import com.sebastijanzindl.galore.domain.usecase.GetDailyHomeSectionsUseCase
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
class HomeScreenViewModel @Inject constructor(
    private val getDailyHomeSectionsUseCase: GetDailyHomeSectionsUseCase,
    private val auth: Auth
): ViewModel(){
    private val _cocktailSections = MutableStateFlow<List<Section>>(emptyList())
    val cocktailSections = _cocktailSections.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false);
    val isLoading = _isLoading.asStateFlow()


    private val _toastMessage = MutableStateFlow<SnackbarMessage?>(null)
    val toastMessage = _toastMessage.asStateFlow()

    init {
        getSections()
    }

    fun dismissToastMessage() {
        _toastMessage.update { null }
    }

    private fun getSections() {
        viewModelScope.launch {
            try {
                _isLoading.value = true;

                val session = auth.currentSessionOrNull() ?: throw Exception("user not logged in");

                Log.i("TOKEN", session.accessToken);

                val result = getDailyHomeSectionsUseCase.execute(
                    GetDailyHomeSectionsUseCase.Input(session.accessToken)
                )
                if(result.result == null) throw Exception("There was a problem fetching the sections.")

                _cocktailSections.value = result.result
            } catch (e: Exception) {
                e.message?.let { Log.e("Home Screen", it) }
                _toastMessage.value = SnackbarMessage.from(
                    duration = SnackbarDuration.Short,
                    userMessage = UserMessage.from("An error occurred"),
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