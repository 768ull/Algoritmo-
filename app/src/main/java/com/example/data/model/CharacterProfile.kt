package com.example.data.model

import androidx.compose.ui.graphics.Color

data class CharacterProfile(
    val id: String,
    val name: String,
    val title: String,
    val role: String,
    val accentColor: Color,
    val quote: String,
    val corruptionLevel: Int, // 0 - 100%
    val biography: String,
    val keyTraits: List<String>,
    val secretNote: String,
    val signatureIconName: String
)
