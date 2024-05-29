package com.sebastijanzindl.galore.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.sebastijanzindl.galore.domain.models.Cocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SectionSharedViewModel : ViewModel() {
    private val _sectionName = MutableStateFlow<String>("");
    val sectionName = _sectionName.asStateFlow();


    private val _cocktails = MutableStateFlow<List<Cocktail>>(emptyList());
    val cocktails = _cocktails.asStateFlow();


    fun addSectionData(sectionName: String, cocktails: List<Cocktail>) {
        _sectionName.value = sectionName
        _cocktails.value = cocktails
    }

    fun clearSectionData() {
        _sectionName.value = ""
        _cocktails.value = emptyList()
    }
}