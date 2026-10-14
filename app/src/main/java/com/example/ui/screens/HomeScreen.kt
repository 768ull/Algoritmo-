package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Radar
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.repository.AlgorithomusRepository
import com.example.ui.components.AudioEffects
import com.example.ui.components.GlitchBadge
import com.example.ui.theme.CyberCyan
import com.example.ui.theme.EntityViolet
import com.example.ui.theme.GlitchRed
import com.example.ui.theme.MorajoPurple
import com.example.ui.theme.SurfaceContainerDark
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite
import com.example.ui.theme.TorajoOrange
import com.example.ui.theme.VoidBlack

@Composable
fun HomeScreen(
    onNavigateToEpisodes: () -> Unit,
    onNavigateToCharacters: () -> Unit,
    onNavigateToScanner: () -> Unit,
    onNavigateToQuiz: () -> Unit,
    onNavigateToOfficial: () -> Unit
) {
    val context = LocalContext.current
    val episodes = AlgorithomusRepository.episodes
    val characters = AlgorithomusRepository.characters

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(VoidBlack)
            .testTag("home_screen_column"),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // 1. Top Header with Official Domain Chip
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(SurfaceContainerDark, VoidBlack)
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "ALGORITHOMUS",
                                color = CyberCyan,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 2.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            GlitchBadge(text = "LIVE", isGlitching = true)
                        }
                        Text(
                            text = "Universo Mundo Torajo em Verade",
                            color = TextMuted,
                            fontSize = 12.sp
                        )
                    }

                    // Official Website Pill
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(SurfaceDark)
                            .border(1.dp, CyberCyan.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                            .clickable {
                                AudioEffects.playGlitchTone()
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://algorithomus.com.br/"))
                                context.startActivity(intent)
                            }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                            .testTag("portal_url_chip"),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = "Website",
                            tint = CyberCyan,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "algorithomus.com.br",
                            color = TextWhite,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // 2. Hero Banner Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .border(1.dp, EntityViolet.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                    .testTag("hero_banner_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.img_hero_banner),
                        contentDescription = "Algorithomus Banner",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f),
                        contentScale = ContentScale.Crop
                    )

                    // Gradient overlay
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color(0x99090614),
                                        Color(0xF0090614)
                                    )
                                )
                            )
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            GlitchBadge(
                                text = "SÉRIE OFICIAL",
                                accentColor = TorajoOrange
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "CRIADO POR BRUNO GEMIN",
                                color = TextWhite.copy(alpha = 0.8f),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "A Corrupção Invadiu Verade",
                            color = TextWhite,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Morajo enfrenta pesadelos constantes enquanto a entidade Algorithomus ameaça desintegrar a realidade.",
                            color = TextMuted,
                            fontSize = 12.sp,
                            maxLines = 2
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    AudioEffects.playSuccessTone()
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(episodes.first().youtubeUrl)
                                    )
                                    context.startActivity(intent)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = CyberCyan,
                                    contentColor = VoidBlack
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.testTag("watch_ep1_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Assistir Ep. 1", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }

                            FilledTonalButton(
                                onClick = onNavigateToScanner,
                                colors = ButtonDefaults.filledTonalButtonColors(
                                    containerColor = EntityViolet.copy(alpha = 0.25f),
                                    contentColor = EntityViolet
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.testTag("open_scanner_hero_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Radar,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Escanear Glitch", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }

        // 3. Glitch Anomaly Status Alert
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .border(1.dp, GlitchRed.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1F0B18))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .background(GlitchRed.copy(alpha = 0.2f), CircleShape)
                            .border(1.dp, GlitchRed, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Glitch Warning",
                            tint = GlitchRed,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "STATUS CRÍTICO DO ALGORITHOMUS",
                            color = GlitchRed,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = "Anomalia detectada no setor de Morajo (84% corrupção).",
                            color = TextWhite,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    OutlinedButton(
                        onClick = onNavigateToScanner,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = CyberCyan),
                        border = androidx.compose.foundation.BorderStroke(1.dp, CyberCyan),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.testTag("intercept_anomaly_button")
                    ) {
                        Text("Decodificar", fontSize = 11.sp)
                    }
                }
            }
        }

        // 4. Quick Portal Hub Grid
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                Text(
                    text = "CENTRAL DE COMANDO",
                    color = CyberCyan,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    QuickActionCard(
                        title = "Episódios",
                        subtitle = "6 Capítulos",
                        icon = Icons.Default.Movie,
                        accentColor = TorajoOrange,
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToEpisodes,
                        testTag = "quick_episodes"
                    )
                    QuickActionCard(
                        title = "Personagens",
                        subtitle = "Turma & Glitch",
                        icon = Icons.Default.People,
                        accentColor = EntityViolet,
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToCharacters,
                        testTag = "quick_characters"
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    QuickActionCard(
                        title = "Scanner",
                        subtitle = "Decodifique",
                        icon = Icons.Default.Radar,
                        accentColor = CyberCyan,
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToScanner,
                        testTag = "quick_scanner"
                    )
                    QuickActionCard(
                        title = "Quiz de Verade",
                        subtitle = "Desafie-se",
                        icon = Icons.Default.Psychology,
                        accentColor = MorajoPurple,
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToQuiz,
                        testTag = "quick_quiz"
                    )
                }
            }
        }

        // 5. Featured Characters Strip
        item {
            Column(modifier = Modifier.padding(vertical = 10.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "PROTAGONISTAS DE VERADE",
                        color = TextWhite,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "Ver todos",
                        color = CyberCyan,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .clickable { onNavigateToCharacters() }
                            .padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(characters) { char ->
                        CharacterMiniCard(
                            character = char,
                            onClick = onNavigateToCharacters
                        )
                    }
                }
            }
        }

        // 6. Diário dos Mistérios Lore Highlight
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .border(1.dp, EntityViolet.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                    .clickable { onNavigateToScanner() },
                colors = CardDefaults.cardColors(containerColor = SurfaceDark)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_mystery_book),
                        contentDescription = "Diário dos Mistérios",
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        GlitchBadge(
                            text = "LIVRO OFICIAL",
                            accentColor = EntityViolet
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Diário dos Mistérios de Verade",
                            color = TextWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Escrito por Bruno Gemin. Contém os enigmas que decodificam a origem do Algorithomus.",
                            color = TextMuted,
                            fontSize = 11.sp
                        )
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Open",
                        tint = EntityViolet,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // 7. Official Links Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .border(1.dp, CyberCyan.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerDark)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "CANAL OFICIAL MUNDO TORAJO",
                        color = CyberCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Mais de 3.200.000 de inscritos acompanhando as aventuras e mistérios criados por Bruno Gemin e Isabela Silva.",
                        color = TextWhite,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://algorithomus.com.br/")
                                )
                                context.startActivity(intent)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CyberCyan,
                                contentColor = VoidBlack
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("visit_official_web_button")
                        ) {
                            Text("algorithomus.com.br", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = onNavigateToOfficial,
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextWhite),
                            border = androidx.compose.foundation.BorderStroke(1.dp, EntityViolet),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Comunidade & Loja", fontSize = 11.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuickActionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accentColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    testTag: String
) {
    Card(
        modifier = modifier
            .border(1.dp, accentColor.copy(alpha = 0.35f), RoundedCornerShape(12.dp))
            .clickable {
                AudioEffects.playGlitchTone()
                onClick()
            }
            .testTag(testTag),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark)
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(accentColor.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                color = TextMuted,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun CharacterMiniCard(
    character: com.example.data.model.CharacterProfile,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(130.dp)
            .border(1.dp, character.accentColor.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
            .clickable {
                AudioEffects.playGlitchTone()
                onClick()
            },
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(character.accentColor.copy(alpha = 0.2f), CircleShape)
                    .border(1.dp, character.accentColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = character.name.take(1),
                    color = character.accentColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = character.name,
                color = TextWhite,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = if (character.corruptionLevel > 50) "Glitch ${character.corruptionLevel}%" else "Estável",
                color = if (character.corruptionLevel > 50) GlitchRed else character.accentColor,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
