package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.AlgorithomusRepository
import com.example.ui.components.AudioEffects
import com.example.ui.components.GlitchBadge
import com.example.ui.theme.CyberCyan
import com.example.ui.theme.EntityViolet
import com.example.ui.theme.GlitchRed
import com.example.ui.theme.SurfaceContainerDark
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite
import com.example.ui.theme.TorajoGold
import com.example.ui.theme.TorajoOrange
import com.example.ui.theme.VoidBlack
import com.example.ui.theme.ZulmiGreen

@Composable
fun QuizScreen() {
    val questions = AlgorithomusRepository.quizQuestions
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var isSubmitted by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }
    var isCompleted by remember { mutableStateOf(false) }

    val currentQuestion = questions.getOrNull(currentIndex)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VoidBlack)
            .testTag("quiz_screen_container")
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceDark)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "QUIZ DE VERADE",
                        color = CyberCyan,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Teste seu conhecimento sobre o universo Algorithomus",
                        color = TextMuted,
                        fontSize = 12.sp
                    )
                }

                GlitchBadge(
                    text = "PONTUAÇÃO: $score",
                    accentColor = TorajoGold
                )
            }

            if (!isCompleted && currentQuestion != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Questão ${currentIndex + 1}/${questions.size}",
                        color = TextMuted,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    LinearProgressIndicator(
                        progress = { (currentIndex + 1).toFloat() / questions.size },
                        modifier = Modifier
                            .weight(1f)
                            .height(6.dp),
                        color = CyberCyan,
                        trackColor = SurfaceContainerDark
                    )
                }
            }
        }

        if (isCompleted) {
            // Completed Summary Card
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, TorajoGold, RoundedCornerShape(16.dp))
                        .testTag("quiz_completed_card"),
                    colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .background(TorajoGold.copy(alpha = 0.15f), CircleShape)
                                .border(2.dp, TorajoGold, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.EmojiEvents,
                                contentDescription = null,
                                tint = TorajoGold,
                                modifier = Modifier.size(36.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        val percentage = (score * 100) / questions.size
                        val badgeTitle = when {
                            percentage >= 90 -> "Guardião Supremo de Verade"
                            percentage >= 70 -> "Detetive de Anomalias"
                            percentage >= 40 -> "Iniciado de Mundo Torajo"
                            else -> "Espectador Curioso"
                        }

                        GlitchBadge(
                            text = badgeTitle,
                            accentColor = if (percentage >= 70) TorajoGold else EntityViolet
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Resultado do Desafio",
                            color = TextWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Você acertou $score de ${questions.size} questões ($percentage%)!",
                            color = TextMuted,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                AudioEffects.playGlitchTone()
                                currentIndex = 0
                                selectedOptionIndex = null
                                isSubmitted = false
                                score = 0
                                isCompleted = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CyberCyan,
                                contentColor = VoidBlack
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(imageVector = Icons.Default.RestartAlt, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Tentar Novamente", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else if (currentQuestion != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Question Card
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, CyberCyan.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                        colors = CardDefaults.cardColors(containerColor = SurfaceDark)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                GlitchBadge(
                                    text = "DIFICULDADE: ${currentQuestion.difficulty.uppercase()}",
                                    accentColor = TorajoOrange
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = currentQuestion.question,
                                color = TextWhite,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 22.sp
                            )
                        }
                    }
                }

                // Options
                items(count = currentQuestion.options.size) { index ->
                    val optionText = currentQuestion.options[index]
                    val isSelected = selectedOptionIndex == index
                    val isCorrect = index == currentQuestion.correctIndex

                    val borderColor = when {
                        !isSubmitted && isSelected -> CyberCyan
                        isSubmitted && isCorrect -> ZulmiGreen
                        isSubmitted && isSelected && !isCorrect -> GlitchRed
                        else -> SurfaceContainerDark
                    }

                    val containerColor = when {
                        !isSubmitted && isSelected -> CyberCyan.copy(alpha = 0.1f)
                        isSubmitted && isCorrect -> ZulmiGreen.copy(alpha = 0.15f)
                        isSubmitted && isSelected && !isCorrect -> GlitchRed.copy(alpha = 0.15f)
                        else -> SurfaceDark
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
                            .clickable(enabled = !isSubmitted) {
                                AudioEffects.playGlitchTone()
                                selectedOptionIndex = index
                            }
                            .testTag("quiz_option_$index"),
                        colors = CardDefaults.cardColors(containerColor = containerColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .background(
                                        if (isSelected || (isSubmitted && isCorrect)) borderColor else SurfaceContainerDark,
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                val letter = ('A' + index).toString()
                                Text(
                                    text = letter,
                                    color = if (isSelected || (isSubmitted && isCorrect)) VoidBlack else TextMuted,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = optionText,
                                color = TextWhite,
                                fontSize = 13.sp,
                                modifier = Modifier.weight(1f)
                            )

                            if (isSubmitted && isCorrect) {
                                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = ZulmiGreen, modifier = Modifier.size(18.dp))
                            } else if (isSubmitted && isSelected && !isCorrect) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = GlitchRed, modifier = Modifier.size(18.dp))
                            }
                        }
                    }
                }

                // Explanation Box after submission
                item {
                    AnimatedVisibility(visible = isSubmitted) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, EntityViolet.copy(alpha = 0.4f), RoundedCornerShape(10.dp)),
                            colors = CardDefaults.cardColors(containerColor = SurfaceContainerDark)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(imageVector = Icons.Default.HelpOutline, contentDescription = null, tint = EntityViolet, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "EXPLICAÇÃO DE VERADE",
                                        color = EntityViolet,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Monospace
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = currentQuestion.explanation,
                                    color = TextWhite,
                                    fontSize = 12.sp,
                                    lineHeight = 17.sp
                                )
                            }
                        }
                    }
                }

                // Action Button (Confirm or Next)
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    if (!isSubmitted) {
                        Button(
                            onClick = {
                                if (selectedOptionIndex != null) {
                                    isSubmitted = true
                                    if (selectedOptionIndex == currentQuestion.correctIndex) {
                                        AudioEffects.playSuccessTone()
                                        score++
                                    } else {
                                        AudioEffects.playErrorTone()
                                    }
                                }
                            },
                            enabled = selectedOptionIndex != null,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CyberCyan,
                                contentColor = VoidBlack,
                                disabledContainerColor = SurfaceContainerDark,
                                disabledContentColor = TextMuted
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("submit_quiz_answer")
                        ) {
                            Text("Confirmar Resposta", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                    } else {
                        Button(
                            onClick = {
                                AudioEffects.playGlitchTone()
                                if (currentIndex + 1 < questions.size) {
                                    currentIndex++
                                    selectedOptionIndex = null
                                    isSubmitted = false
                                } else {
                                    isCompleted = true
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = EntityViolet,
                                contentColor = TextWhite
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("next_quiz_question")
                        ) {
                            Text(
                                text = if (currentIndex + 1 < questions.size) "Próxima Questão" else "Ver Resultado Final",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
