package com.louiscad.poc.rolemodules.core.feature.jokes.impl

import com.louiscad.poc.rolemodules.core.feature.jokes.api.HistoricalJokeEntry
import com.louiscad.poc.rolemodules.core.feature.jokes.api.Joke
import com.louiscad.poc.rolemodules.core.feature.jokes.api.JokeGenerator
import com.louiscad.poc.rolemodules.core.feature.jokes.api.JokeId
import com.louiscad.poc.rolemodules.core.feature.jokes.api.TextJokeContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlin.time.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class JokeGeneratorPocImpl(
    private val randomJoke: suspend () -> TextJokeContent = { TextJokeContent(listOf("Lol")) }
) : JokeGenerator() {

    override val jokesGenerationHistory: Flow<List<HistoricalJokeEntry>>
        field = MutableStateFlow<List<HistoricalJokeEntry>>(emptyList())

    override suspend fun getRandomJoke(): Joke = Joke(
        id = getNextJokeId(),
        content = randomJoke()
    ).also { newJoke ->
        jokesGenerationHistory.update {
            it + HistoricalJokeEntry(
                id = newJoke.id,
                generatedAt = Clock.System.now(),
                content = newJoke.content
            )
        }
    }

    override suspend fun getJokeWithPrompt(plainTextPrompt: String): Joke {
        TODO()
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun getNextJokeId(): JokeId = JokeId(Uuid.generateV7().toHexDashString())
}
