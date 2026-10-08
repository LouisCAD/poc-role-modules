package com.louiscad.poc.rolemodules.core.feature.jokes.impl

import com.louiscad.poc.rolemodules.core.feature.jokes.api.HistoricalJokeEntry
import com.louiscad.poc.rolemodules.core.feature.jokes.api.Joke
import com.louiscad.poc.rolemodules.core.feature.jokes.api.JokeGenerator
import com.louiscad.poc.rolemodules.core.feature.jokes.api.JokeId
import com.louiscad.poc.rolemodules.core.feature.jokes.api.TextJokeContent
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class JokeGeneratorPocImpl(
    private val randomJoke: suspend () -> TextJokeContent = { TextJokeContent(listOf("Lol")) }
) : JokeGenerator() {

    override val jokesGenerationHistory: Flow<List<HistoricalJokeEntry>> = TODO()

    override suspend fun getRandomJoke(): Joke = Joke(
        id = getNextJokeId(),
        content = randomJoke()
    )

    override suspend fun getJokeWithPrompt(plainTextPrompt: String): Joke {
        TODO()
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun getNextJokeId(): JokeId = JokeId(Uuid.generateV7().toHexDashString())
}
