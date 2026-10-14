package com.example.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Radar
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.AnomalySignal
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
import com.example.ui.theme.ZulmiGreen
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ScannerScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val anomalies = AlgorithomusRepository.anomalies
    val resolvedAnomalyIds = remember { mutableStateListOf<String>("anom_01") } // 1 unlocked initially for immediate delight
    var activeDecodingAnomaly by remember { mutableStateOf<AnomalySignal?>(null) }
    var currentInputSequence = remember { mutableStateListOf<Int>() }
    var isScanningActive by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VoidBlack)
            .testTag("scanner_screen_container")
    ) {
        // Tab Header
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = SurfaceDark,
            contentColor = CyberCyan,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = CyberCyan
                )
            }
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = {
                    AudioEffects.playGlitchTone()
                    selectedTab = 0
                },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Radar, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Detector de Glitch", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = {
                    AudioEffects.playGlitchTone()
                    selectedTab = 1
                },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.MenuBook, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Diário dos Mistérios (${resolvedAnomalyIds.size})", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            )
        }

        if (selectedTab == 0) {
            // Scanner View
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Radar visualizer
                item {
                    RadarVisualizerCard(
                        isScanning = isScanningActive,
                        anomaliesCount = anomalies.size - resolvedAnomalyIds.size,
                        onToggleScanning = {
                            AudioEffects.playScannerPulse()
                            isScanningActive = !isScanningActive
                        }
                    )
                }

                // Interactive Decoding Workspace if an anomaly is selected
                if (activeDecodingAnomaly != null) {
                    val target = activeDecodingAnomaly!!
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.5.dp, CyberCyan, RoundedCornerShape(12.dp))
                                .testTag("decoder_workspace_card"),
                            colors = CardDefaults.cardColors(containerColor = SurfaceContainerDark)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    GlitchBadge(
                                        text = "DECODIFICADOR ATIVO: ${target.code}",
                                        accentColor = CyberCyan,
                                        isGlitching = true
                                    )
                                    IconButton(
                                        onClick = {
                                            activeDecodingAnomaly = null
                                            currentInputSequence.clear()
                                        }
                                    ) {
                                        Text("✕", color = TextMuted, fontSize = 16.sp)
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = target.title,
                                    color = TextWhite,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Sintonize os 4 nós de frequência harmônica para purificar o código de Verade:",
                                    color = TextMuted,
                                    fontSize = 12.sp
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                // Current sequence progress indicator
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    for (i in 0 until target.correctKeySequence.size) {
                                        val isFilled = i < currentInputSequence.size
                                        Box(
                                            modifier = Modifier
                                                .padding(horizontal = 6.dp)
                                                .size(24.dp)
                                                .background(
                                                    if (isFilled) CyberCyan else SurfaceDark,
                                                    CircleShape
                                                )
                                                .border(
                                                    1.dp,
                                                    if (isFilled) CyberCyan else TextMuted,
                                                    CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (isFilled) {
                                                Text(
                                                    text = "${currentInputSequence[i]}",
                                                    color = VoidBlack,
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                // Node buttons to tap (Torajo, Morajo, Zulmi)
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    FrequencyNodeButton(
                                        nodeIndex = 0,
                                        name = "Nó Torajo",
                                        accentColor = TorajoOrange,
                                        modifier = Modifier.weight(1f),
                                        onTap = {
                                            handleNodeTap(0, target, currentInputSequence) {
                                                resolvedAnomalyIds.add(target.id)
                                                activeDecodingAnomaly = null
                                            }
                                        }
                                    )
                                    FrequencyNodeButton(
                                        nodeIndex = 1,
                                        name = "Nó Morajo",
                                        accentColor = MorajoPurple,
                                        modifier = Modifier.weight(1f),
                                        onTap = {
                                            handleNodeTap(1, target, currentInputSequence) {
                                                resolvedAnomalyIds.add(target.id)
                                                activeDecodingAnomaly = null
                                            }
                                        }
                                    )
                                    FrequencyNodeButton(
                                        nodeIndex = 2,
                                        name = "Nó Zulmi",
                                        accentColor = ZulmiGreen,
                                        modifier = Modifier.weight(1f),
                                        onTap = {
                                            handleNodeTap(2, target, currentInputSequence) {
                                                resolvedAnomalyIds.add(target.id)
                                                activeDecodingAnomaly = null
                                            }
                                        }
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Dica: Frequência ${target.frequencyHz}Hz",
                                        color = TextMuted,
                                        fontSize = 11.sp,
                                        fontFamily = FontFamily.Monospace
                                    )
                                    OutlinedButton(
                                        onClick = { currentInputSequence.clear() },
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Icon(imageVector = Icons.Default.RestartAlt, contentDescription = null, modifier = Modifier.size(12.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Resetar", fontSize = 10.sp)
                                    }
                                }
                            }
                        }
                    }
                }

                // Detected Anomalies List
                item {
                    Text(
                        text = "ANOMALIAS DETECTADAS NO SETOR",
                        color = CyberCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.sp
                    )
                }

                items(anomalies) { anomaly ->
                    val isResolved = resolvedAnomalyIds.contains(anomaly.id)
                    AnomalyItemCard(
                        anomaly = anomaly,
                        isResolved = isResolved,
                        isSelected = activeDecodingAnomaly?.id == anomaly.id,
                        onSelectForDecoding = {
                            AudioEffects.playScannerPulse()
                            activeDecodingAnomaly = anomaly
                            currentInputSequence.clear()
                        }
                    )
                }
            }
        } else {
            // Diário dos Mistérios View
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, EntityViolet.copy(alpha = 0.4f), RoundedCornerShape(12.dp)),
                        colors = CardDefaults.cardColors(containerColor = SurfaceDark)
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_mystery_book),
                                contentDescription = "Diário de Verade",
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                GlitchBadge(text = "CODEX DE VERADE", accentColor = EntityViolet)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Diário dos Mistérios",
                                    color = TextWhite,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Registros secretos decodificados pelo scanner de anomalias.",
                                    color = TextMuted,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }

                item {
                    Text(
                        text = "PÁGINAS DESBLOQUEADAS",
                        color = CyberCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }

                items(anomalies) { anomaly ->
                    val isUnlocked = resolvedAnomalyIds.contains(anomaly.id)
                    DiaryEntryCard(anomaly = anomaly, isUnlocked = isUnlocked)
                }
            }
        }
    }
}

private fun handleNodeTap(
    node: Int,
    target: AnomalySignal,
    currentInputSequence: MutableList<Int>,
    onComplete: () -> Unit
) {
    AudioEffects.playGlitchTone()
    currentInputSequence.add(node)

    val stepIndex = currentInputSequence.size - 1
    if (target.correctKeySequence[stepIndex] != node) {
        // Wrong node
        AudioEffects.playErrorTone()
        currentInputSequence.clear()
    } else if (currentInputSequence.size == target.correctKeySequence.size) {
        // Solved!
        AudioEffects.playSuccessTone()
        onComplete()
    }
}

@Composable
fun FrequencyNodeButton(
    nodeIndex: Int,
    name: String,
    accentColor: Color,
    modifier: Modifier = Modifier,
    onTap: () -> Unit
) {
    Button(
        onClick = onTap,
        colors = ButtonDefaults.buttonColors(
            containerColor = accentColor.copy(alpha = 0.2f),
            contentColor = accentColor
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, accentColor),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "NÓ #$nodeIndex", fontSize = 10.sp, fontWeight = FontWeight.Black, fontFamily = FontFamily.Monospace)
            Text(text = name, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun RadarVisualizerCard(
    isScanning: Boolean,
    anomaliesCount: Int,
    onToggleScanning: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "radarRotation")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "radarAngle"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, CyberCyan.copy(alpha = 0.4f), RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Sensors, contentDescription = null, tint = CyberCyan, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "RADAR DE FREQUÊNCIA DE VERADE",
                        color = CyberCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }

                GlitchBadge(
                    text = if (anomaliesCount > 0) "$anomaliesCount ATIVAS" else "PURIFICADO",
                    accentColor = if (anomaliesCount > 0) GlitchRed else ZulmiGreen,
                    isGlitching = anomaliesCount > 0
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Canvas Radar
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .background(VoidBlack)
                    .border(1.dp, CyberCyan.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val center = Offset(size.width / 2f, size.height / 2f)
                    val maxRadius = size.width / 2f

                    // Concentric grid circles
                    drawCircle(color = CyberCyan.copy(alpha = 0.15f), radius = maxRadius * 0.33f, style = Stroke(1.dp.toPx()))
                    drawCircle(color = CyberCyan.copy(alpha = 0.15f), radius = maxRadius * 0.66f, style = Stroke(1.dp.toPx()))
                    drawCircle(color = CyberCyan.copy(alpha = 0.25f), radius = maxRadius * 0.95f, style = Stroke(1.dp.toPx()))

                    // Crosshair lines
                    drawLine(color = CyberCyan.copy(alpha = 0.2f), start = Offset(0f, center.y), end = Offset(size.width, center.y), strokeWidth = 1.dp.toPx())
                    drawLine(color = CyberCyan.copy(alpha = 0.2f), start = Offset(center.x, 0f), end = Offset(center.x, size.height), strokeWidth = 1.dp.toPx())

                    // Sweep line
                    if (isScanning) {
                        val rad = Math.toRadians(angle.toDouble())
                        val endX = center.x + maxRadius * cos(rad).toFloat()
                        val endY = center.y + maxRadius * sin(rad).toFloat()
                        drawLine(
                            color = CyberCyan,
                            start = center,
                            end = Offset(endX, endY),
                            strokeWidth = 2.dp.toPx()
                        )
                    }

                    // Anomaly Blips
                    if (anomaliesCount > 0) {
                        drawCircle(color = GlitchRed, radius = 4.dp.toPx(), center = Offset(center.x + 35.dp.toPx(), center.y - 25.dp.toPx()))
                        drawCircle(color = MorajoPurple, radius = 4.dp.toPx(), center = Offset(center.x - 40.dp.toPx(), center.y + 30.dp.toPx()))
                    }
                }

                if (!isScanning) {
                    Text("PAUSADO", color = TextMuted, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onToggleScanning,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isScanning) SurfaceContainerDark else CyberCyan,
                    contentColor = if (isScanning) CyberCyan else VoidBlack
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = if (isScanning) "Pausar Varredura" else "Retomar Varredura",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun AnomalyItemCard(
    anomaly: AnomalySignal,
    isResolved: Boolean,
    isSelected: Boolean,
    onSelectForDecoding: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                if (isResolved) ZulmiGreen.copy(alpha = 0.4f) else if (isSelected) CyberCyan else SurfaceContainerDark,
                RoundedCornerShape(10.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        if (isResolved) ZulmiGreen.copy(alpha = 0.15f) else GlitchRed.copy(alpha = 0.15f),
                        CircleShape
                    )
                    .border(1.dp, if (isResolved) ZulmiGreen else GlitchRed, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isResolved) Icons.Default.CheckCircle else Icons.Default.Warning,
                    contentDescription = null,
                    tint = if (isResolved) ZulmiGreen else GlitchRed,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = anomaly.code,
                        color = if (isResolved) ZulmiGreen else CyberCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "• ${anomaly.frequencyHz} Hz",
                        color = TextMuted,
                        fontSize = 10.sp
                    )
                }
                Text(
                    text = anomaly.title,
                    color = TextWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = anomaly.location,
                    color = TextMuted,
                    fontSize = 11.sp
                )
            }

            if (!isResolved) {
                Button(
                    onClick = onSelectForDecoding,
                    colors = ButtonDefaults.buttonColors(containerColor = CyberCyan, contentColor = VoidBlack),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(text = if (isSelected) "Ajustando" else "Sintonizar", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            } else {
                Text(
                    text = "PURIFICADO",
                    color = ZulmiGreen,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}

@Composable
fun DiaryEntryCard(
    anomaly: AnomalySignal,
    isUnlocked: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                if (isUnlocked) EntityViolet.copy(alpha = 0.4f) else SurfaceContainerDark,
                RoundedCornerShape(10.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = if (isUnlocked) SurfaceDark else VoidBlack)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isUnlocked) anomaly.unlockedDiaryTitle else "Página Criptografada",
                    color = if (isUnlocked) CyberCyan else TextMuted,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )

                if (isUnlocked) {
                    Icon(
                        imageVector = Icons.Default.LockOpen,
                        contentDescription = "Desbloqueado",
                        tint = ZulmiGreen,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            if (isUnlocked) {
                Text(
                    text = anomaly.unlockedDiaryText,
                    color = TextWhite,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )
            } else {
                Text(
                    text = "Purifique a anomalia '${anomaly.code}' no Scanner de Glitch para descriptografar este registro do Diário dos Mistérios de Verade.",
                    color = TextMuted,
                    fontSize = 11.sp
                )
            }
        }
    }
}
