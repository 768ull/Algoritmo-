package com.example.data.model

data class QuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val difficulty: String // "Fácil", "Médio", "Difícil"
)
