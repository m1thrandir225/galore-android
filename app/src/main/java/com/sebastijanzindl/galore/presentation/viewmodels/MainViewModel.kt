package com.sebastijanzindl.galore.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.sebastijanzindl.galore.data.repository.PermissionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val permissionsRepository: PermissionsRepository
): ViewModel() {
    val hasNotificationsPermission = permissionsRepository.hasNotificationsPermission

    fun updateNotificationPermission(granted: Boolean) {
        permissionsRepository.hasNotificationsPermission.value = granted
    }
}