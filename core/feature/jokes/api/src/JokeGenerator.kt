package com.louiscad.poc.rolemodules.core.feature.jokes.api

import kotlinx.coroutines.flow.Flow

abstract class JokeGenerator {
    abstract suspend fun getRandomJoke(): Joke
    abstract suspend fun getJokeWithPrompt(plainTextPrompt: String): Joke

    abstract val jokesGenerationHistory: Flow<List<HistoricalJokeEntry>>
}
