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

class JokeGeneratorPocImpl : JokeGenerator() {

    override val jokesGenerationHistory: Flow<List<HistoricalJokeEntry>>
        field = MutableStateFlow<List<HistoricalJokeEntry>>(emptyList())

    override suspend fun getRandomJoke(): Joke = Joke(
        id = getNextJokeId(),
        content = defaultJokes.random()
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

private val defaultJokes = buildList {
    this += TextJokeContent(listOf("Le soleil se lève sans poser les pieds par terre."))
    this += TextJokeContent(listOf("Qu'est-ce qui est jaune et qui attend ?", "Jonathan"))
    this += TextJokeContent(listOf("Comment appelle-t-on un cochon avec des ailes ?", "Un aéro-porc."))
    this += TextJokeContent(listOf("Quel acteur a la main verte ?", "Jean Dujardin"))
    this += TextJokeContent(listOf("Une femme arrive chez un barbier. Il dit quoi ?", "Feur !"))
    this += TextJokeContent(listOf("Jacques a sa langue qui fourche. Il dit quoi ?", "Quoicoubeh !"))
}
