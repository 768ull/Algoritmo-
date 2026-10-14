package com.example.data.model

data class Episode(
    val id: String,
    val title: String,
    val seriesCategory: String, // "Algorithomus", "Torajo vs Morajo", "Especiais"
    val episodeNumber: Int,
    val duration: String,
    val releaseDate: String,
    val synopsis: String,
    val youtubeUrl: String,
    val glitchLevel: Int, // 0 - 100%
    val featuredCharacters: List<String>,
    val secretLore: String,
    val badgeLabel: String
)
