package com.sebastijanzindl.galore.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.sebastijanzindl.galore.domain.models.CocktailCardInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SectionSharedViewModel @Inject constructor() : ViewModel() {
    private val _sectionName = MutableStateFlow<String>("");
    val sectionName = _sectionName.asStateFlow();


    private val _cocktails = MutableStateFlow<List<CocktailCardInfo>>(emptyList());
    val cocktails = _cocktails.asStateFlow();


    fun addSectionData(sectionName: String, cocktails: List<CocktailCardInfo>) {
        _sectionName.value = sectionName
        _cocktails.value = cocktails
    }

    fun clearSectionData() {
        _sectionName.value = ""
        _cocktails.value = emptyList()
    }
}