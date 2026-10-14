package com.example.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Episode
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
import com.example.ui.theme.TorajoOrange
import com.example.ui.theme.VoidBlack

@Composable
fun EpisodesScreen() {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Todos") }
    val watchedEpisodeIds = remember { mutableStateListOf<String>() }

    val categories = listOf("Todos", "Algorithomus", "Torajo vs Morajo", "Especiais")

    val allEpisodes = AlgorithomusRepository.episodes
    val filteredEpisodes = allEpisodes.filter { ep ->
        val matchesCategory = selectedCategory == "Todos" || ep.seriesCategory == selectedCategory
        val matchesSearch = ep.title.contains(searchQuery, ignoreCase = true) ||
                ep.synopsis.contains(searchQuery, ignoreCase = true) ||
                ep.featuredCharacters.any { it.contains(searchQuery, ignoreCase = true) }
        matchesCategory && matchesSearch
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VoidBlack)
            .testTag("episodes_screen_container")
    ) {
        // Top Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceDark)
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "CATÁLOGO DE EPISÓDIOS",
                        color = CyberCyan,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Algorithomus, Torajo vs Morajo e Especiais de Verade",
                        color = TextMuted,
                        fontSize = 12.sp
                    )
                }

                GlitchBadge(
                    text = "${watchedEpisodeIds.size}/${allEpisodes.size} VISTOS",
                    accentColor = EntityViolet
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("episode_search_input"),
                placeholder = { Text("Buscar por título, personagem ou lore...", color = TextMuted, fontSize = 13.sp) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = CyberCyan)
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CyberCyan,
                    unfocusedBorderColor = SurfaceContainerDark,
                    focusedTextColor = TextWhite,
                    unfocusedTextColor = TextWhite,
                    cursorColor = CyberCyan,
                    focusedContainerColor = VoidBlack,
                    unfocusedContainerColor = VoidBlack
                ),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Filter Chips
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = {
                            AudioEffects.playGlitchTone()
                            selectedCategory = category
                        },
                        label = { Text(category, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = EntityViolet,
                            selectedLabelColor = TextWhite,
                            containerColor = SurfaceContainerDark,
                            labelColor = TextMuted
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = selectedCategory == category,
                            borderColor = if (selectedCategory == category) EntityViolet else SurfaceContainerDark
                        )
                    )
                }
            }
        }

        // Episodes List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            if (filteredEpisodes.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Nenhum episódio encontrado",
                                color = TextMuted,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = {
                                    searchQuery = ""
                                    selectedCategory = "Todos"
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerDark)
                            ) {
                                Text("Limpar filtros", color = CyberCyan)
                            }
                        }
                    }
                }
            } else {
                items(filteredEpisodes, key = { it.id }) { episode ->
                    val isWatched = watchedEpisodeIds.contains(episode.id)
                    EpisodeCardItem(
                        episode = episode,
                        isWatched = isWatched,
                        onToggleWatched = {
                            AudioEffects.playGlitchTone()
                            if (isWatched) {
                                watchedEpisodeIds.remove(episode.id)
                            } else {
                                watchedEpisodeIds.add(episode.id)
                            }
                        },
                        onWatchClick = {
                            AudioEffects.playSuccessTone()
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(episode.youtubeUrl))
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EpisodeCardItem(
    episode: Episode,
    isWatched: Boolean,
    onToggleWatched: () -> Unit,
    onWatchClick: () -> Unit
) {
    var expandedLore by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isWatched) EntityViolet.copy(alpha = 0.5f) else SurfaceContainerDark,
                shape = RoundedCornerShape(12.dp)
            )
            .testTag("episode_card_${episode.id}"),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    GlitchBadge(
                        text = episode.badgeLabel,
                        accentColor = if (episode.glitchLevel > 70) GlitchRed else TorajoOrange,
                        isGlitching = episode.glitchLevel > 80
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${episode.duration} • ${episode.releaseDate}",
                        color = TextMuted,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                // Watched chip toggle
                Row(
                    modifier = Modifier
                        .clickable { onToggleWatched() }
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .background(
                                color = if (isWatched) EntityViolet else Color.Transparent,
                                shape = CircleShape
                            )
                            .border(1.dp, if (isWatched) EntityViolet else TextMuted, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isWatched) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Visto",
                                tint = TextWhite,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isWatched) "Assistido" else "Marcar visto",
                        color = if (isWatched) EntityViolet else TextMuted,
                        fontSize = 10.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = episode.title,
                color = TextWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = episode.synopsis,
                color = TextMuted,
                fontSize = 12.sp,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Glitch Intensity Progress Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Índice de Glitch:",
                    color = TextMuted,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(modifier = Modifier.width(8.dp))
                LinearProgressIndicator(
                    progress = { episode.glitchLevel / 100f },
                    modifier = Modifier
                        .weight(1f)
                        .height(6.dp),
                    color = if (episode.glitchLevel > 70) GlitchRed else CyberCyan,
                    trackColor = SurfaceContainerDark
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${episode.glitchLevel}%",
                    color = if (episode.glitchLevel > 70) GlitchRed else CyberCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Cast chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                episode.featuredCharacters.forEach { charName ->
                    Text(
                        text = "#$charName",
                        color = EntityViolet,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Expandable Lore Section
            AnimatedVisibility(visible = expandedLore) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .background(VoidBlack, RoundedCornerShape(8.dp))
                        .border(1.dp, CyberCyan.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = CyberCyan,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "SEGREDO DE BASTIDORES & LORE",
                            color = CyberCyan,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = episode.secretLore,
                        color = TextWhite,
                        fontSize = 11.sp,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .clickable { expandedLore = !expandedLore }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (expandedLore) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        tint = CyberCyan,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (expandedLore) "Ocultar segredo" else "Ver segredo de Verade",
                        color = CyberCyan,
                        fontSize = 11.sp
                    )
                }

                Button(
                    onClick = onWatchClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CyberCyan,
                        contentColor = VoidBlack
                    ),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                    modifier = Modifier.testTag("watch_btn_${episode.id}")
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Assistir", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
