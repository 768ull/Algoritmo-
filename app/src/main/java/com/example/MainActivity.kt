package com.example

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Radar
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.Radar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AudioEffects
import com.example.ui.components.GlitchBadge
import com.example.ui.screens.CharactersScreen
import com.example.ui.screens.EpisodesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.OfficialScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.ScannerScreen
import com.example.ui.theme.AlgorithomusTheme
import com.example.ui.theme.CyberCyan
import com.example.ui.theme.EntityViolet
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite
import com.example.ui.theme.VoidBlack

enum class Screen(val title: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector) {
    HOME("Início", Icons.Filled.Home, Icons.Outlined.Home),
    EPISODES("Episódios", Icons.Filled.Movie, Icons.Outlined.Movie),
    CHARACTERS("Personagens", Icons.Filled.People, Icons.Outlined.People),
    SCANNER("Scanner", Icons.Filled.Radar, Icons.Outlined.Radar),
    QUIZ("Quiz", Icons.Filled.Psychology, Icons.Outlined.Psychology),
    OFFICIAL("Oficial", Icons.Filled.Language, Icons.Outlined.Language)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlgorithomusTheme {
                MainApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    val context = LocalContext.current
    var currentScreen by remember { mutableStateOf(Screen.HOME) }

    // Handle back button when not on HOME
    if (currentScreen != Screen.HOME) {
        BackHandler {
            currentScreen = Screen.HOME
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing),
        containerColor = VoidBlack,
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "ALGORITHOMUS",
                            color = CyberCyan,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "• Verade",
                            color = TextMuted,
                            fontSize = 12.sp
                        )
                    }
                },
                actions = {
                    // Quick button for algorithomus.com.br
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(SurfaceDark)
                            .border(1.dp, CyberCyan.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                            .clickable {
                                AudioEffects.playGlitchTone()
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://algorithomus.com.br/"))
                                context.startActivity(intent)
                            }
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .testTag("appbar_site_btn"),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = "Website",
                                tint = CyberCyan,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Site Oficial",
                                color = TextWhite,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SurfaceDark,
                    titleContentColor = TextWhite
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = SurfaceDark,
                contentColor = TextWhite,
                tonalElevation = 8.dp
            ) {
                // Bottom tabs: Home, Episodes, Characters, Scanner, Quiz, Official
                val bottomScreens = listOf(
                    Screen.HOME,
                    Screen.EPISODES,
                    Screen.CHARACTERS,
                    Screen.SCANNER,
                    Screen.QUIZ
                )

                bottomScreens.forEach { screen ->
                    val isSelected = currentScreen == screen
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            if (currentScreen != screen) {
                                AudioEffects.playGlitchTone()
                                currentScreen = screen
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) screen.selectedIcon else screen.unselectedIcon,
                                contentDescription = screen.title
                            )
                        },
                        label = {
                            Text(
                                text = screen.title,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = VoidBlack,
                            selectedTextColor = CyberCyan,
                            indicatorColor = CyberCyan,
                            unselectedIconColor = TextMuted,
                            unselectedTextColor = TextMuted
                        ),
                        modifier = Modifier.testTag("nav_${screen.name.lowercase()}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(
                targetState = currentScreen,
                label = "screen_crossfade"
            ) { screen ->
                when (screen) {
                    Screen.HOME -> HomeScreen(
                        onNavigateToEpisodes = { currentScreen = Screen.EPISODES },
                        onNavigateToCharacters = { currentScreen = Screen.CHARACTERS },
                        onNavigateToScanner = { currentScreen = Screen.SCANNER },
                        onNavigateToQuiz = { currentScreen = Screen.QUIZ },
                        onNavigateToOfficial = { currentScreen = Screen.OFFICIAL }
                    )
                    Screen.EPISODES -> EpisodesScreen()
                    Screen.CHARACTERS -> CharactersScreen()
                    Screen.SCANNER -> ScannerScreen()
                    Screen.QUIZ -> QuizScreen()
                    Screen.OFFICIAL -> OfficialScreen()
                }
            }
        }
    }
}
