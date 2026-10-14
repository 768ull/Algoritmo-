package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.repository.AlgorithomusRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Algorithomus", appName)
  }

  @Test
  fun `verify algorithomus repository content`() {
    assertTrue(AlgorithomusRepository.episodes.isNotEmpty())
    assertTrue(AlgorithomusRepository.characters.any { it.name == "Torajo" })
    assertTrue(AlgorithomusRepository.characters.any { it.name == "Morajo" })
    assertTrue(AlgorithomusRepository.anomalies.isNotEmpty())
    assertTrue(AlgorithomusRepository.quizQuestions.isNotEmpty())
  }
}
