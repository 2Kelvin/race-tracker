package com.example.racetracker
import com.example.racetracker.ui.RaceParticipant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RaceParticipantTest {
    private val raceParticipant = RaceParticipant(
        name = "Test",
        maxProgress = 100,
        progressDelayMillis = 500L,
        initialProgress = 0,
        progressIncrement = 1
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun raceParticipant_RaceStarted_ProgressUpdated() = runTest {
        val expectedProgress = 1
        launch { raceParticipant.run() }
        advanceTimeBy(raceParticipant.progressDelayMillis)
        runCurrent()

        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun raceParticipant_RaceFinished_ProgressUpdated() = runTest {
        val expectedFinishedProgress = 100
        launch { raceParticipant.run() }
        advanceTimeBy(raceParticipant.maxProgress * raceParticipant.progressDelayMillis)
        runCurrent()

        assertEquals(expectedFinishedProgress, raceParticipant.currentProgress)
    }
}