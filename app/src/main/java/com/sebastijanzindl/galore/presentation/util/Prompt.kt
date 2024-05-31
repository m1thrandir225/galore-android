package com.sebastijanzindl.galore.presentation.util

class Prompt(cocktails: List<String>, flavours: List<String> ){

    private val flavourNames = flavours.joinToString()
    private val cocktailNames = cocktails.joinToString()
    fun generatePrompt(): String {
         return "The user has selected: $flavourNames. And as reference cocktails he selected: $cocktailNames"
    }
}