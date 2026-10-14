package com.example.data.model

data class AnomalySignal(
    val id: String,
    val code: String,
    val title: String,
    val location: String,
    val threatLevel: String, // "Leve", "Moderada", "CRÍTICA"
    val description: String,
    val frequencyHz: Int,
    val correctKeySequence: List<Int>, // Sequence of 3-4 node taps to decode
    val unlockedDiaryTitle: String,
    val unlockedDiaryText: String
)
