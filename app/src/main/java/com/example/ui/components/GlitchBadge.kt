package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CyberCyan
import com.example.ui.theme.GlitchRed

@Composable
fun GlitchBadge(
    text: String,
    modifier: Modifier = Modifier,
    isGlitching: Boolean = false,
    accentColor: Color = CyberCyan
) {
    val infiniteTransition = rememberInfiniteTransition(label = "badgeGlitch")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    val effectiveAlpha = if (isGlitching) pulseAlpha else 1f
    val effectiveColor = if (isGlitching) GlitchRed else accentColor

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(
                color = effectiveColor.copy(alpha = 0.15f),
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = effectiveColor.copy(alpha = effectiveAlpha * 0.7f),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        if (isGlitching) {
            Spacer(
                modifier = Modifier
                    .size(6.dp)
                    .background(GlitchRed, RoundedCornerShape(50))
                    .alpha(effectiveAlpha)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }

        Text(
            text = text.uppercase(),
            color = effectiveColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 1.sp
        )
    }
}
