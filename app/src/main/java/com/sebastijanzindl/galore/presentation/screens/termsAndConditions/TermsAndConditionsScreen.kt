package com.sebastijanzindl.galore.presentation.screens.termsAndConditions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TermsAndConditionsScreen(
    modifier: Modifier = Modifier
) {
    //TODO: implement logic to fetch terms and conditions from server
    Column (
        modifier = modifier.fillMaxSize().padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val paragraphs = listOf(
            "Lorem ipsum dolor sit amet consectetur. Phasellus bibendum elementum nibh cum dis id nunc. Iaculis diam integer elementum feugiat felis cras. Parturient morbi dolor eget euismod nunc mi purus mauris. Vestibulum vitae metus mauris ac lectus.",
            "Lorem ipsum dolor sit amet consectetur. Phasellus bibendum elementum nibh cum dis id nunc. Iaculis diam integer elementum feugiat felis cras. Parturient morbi dolor eget euismod nunc mi purus mauris. Vestibulum vitae metus mauris ac lectus.",
            "Lorem ipsum dolor sit amet consectetur. Phasellus bibendum elementum nibh cum dis id nunc. Iaculis diam integer elementum feugiat felis cras. Parturient morbi dolor eget euismod nunc mi purus mauris. Vestibulum vitae metus mauris ac lectus.",
            "Lorem ipsum dolor sit amet consectetur. Phasellus bibendum elementum nibh cum dis id nunc. Iaculis diam integer elementum feugiat felis cras. Parturient morbi dolor eget euismod nunc mi purus mauris. Vestibulum vitae metus mauris ac lectus.",
            "Lorem ipsum dolor sit amet consectetur. Phasellus bibendum elementum nibh cum dis id nunc. Iaculis diam integer elementum feugiat felis cras. Parturient morbi dolor eget euismod nunc mi purus mauris. Vestibulum vitae metus mauris ac lectus.",
            "Lorem ipsum dolor sit amet consectetur. Phasellus bibendum elementum nibh cum dis id nunc. Iaculis diam integer elementum feugiat felis cras. Parturient morbi dolor eget euismod nunc mi purus mauris. Vestibulum vitae metus mauris ac lectus.",
            "Lorem ipsum dolor sit amet consectetur. Phasellus bibendum elementum nibh cum dis id nunc. Iaculis diam integer elementum feugiat felis cras. Parturient morbi dolor eget euismod nunc mi purus mauris. Vestibulum vitae metus mauris ac lectus.",
            "Lorem ipsum dolor sit amet consectetur. Phasellus bibendum elementum nibh cum dis id nunc. Iaculis diam integer elementum feugiat felis cras. Parturient morbi dolor eget euismod nunc mi purus mauris. Vestibulum vitae metus mauris ac lectus.",
        )
        Text(text = "Terms And Conditions", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onSurface)

        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(paragraphs){ s ->
                Text(text = s, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}