package com.example.ui.components

import android.media.AudioManager
import android.media.ToneGenerator
import android.util.Log

object AudioEffects {
    private var toneGenerator: ToneGenerator? = null

    init {
        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 65)
        } catch (e: Exception) {
            Log.e("AudioEffects", "Unable to initialize ToneGenerator", e)
        }
    }

    fun playGlitchTone() {
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_CDMA_KEYPAD_VOLUME_KEY_LITE, 70)
        } catch (e: Exception) {
            // Ignore
        }
    }

    fun playScannerPulse() {
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 100)
        } catch (e: Exception) {
            // Ignore
        }
    }

    fun playSuccessTone() {
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_PROMPT, 150)
        } catch (e: Exception) {
            // Ignore
        }
    }

    fun playErrorTone() {
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_SUP_ERROR, 180)
        } catch (e: Exception) {
            // Ignore
        }
    }
}
