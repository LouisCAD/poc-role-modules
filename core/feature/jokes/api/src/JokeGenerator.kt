package com.louiscad.poc.rolemodules.core.feature.jokes.api

import androidx.compose.runtime.Stable
import com.louiscad.poc.rolemodules.core.feature.jokes.api.models.HistoricalJokeEntry
import com.louiscad.poc.rolemodules.core.feature.jokes.api.models.Joke
import kotlinx.coroutines.flow.Flow

@Stable
abstract class JokeGenerator {
    abstract suspend fun getRandomJoke(): Joke
    abstract suspend fun getJokeWithPrompt(plainTextPrompt: String): Joke

    abstract val jokesGenerationHistory: Flow<List<HistoricalJokeEntry>>
}
