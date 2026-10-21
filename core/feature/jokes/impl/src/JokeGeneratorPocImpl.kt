package com.louiscad.poc.rolemodules.core.feature.jokes.impl

import androidx.room3.withWriteTransaction
import com.louiscad.poc.rolemodules.core.common.room.RoomDatabaseConfig
import com.louiscad.poc.rolemodules.core.feature.jokes.api.models.HistoricalJokeEntry
import com.louiscad.poc.rolemodules.core.feature.jokes.api.models.Joke
import com.louiscad.poc.rolemodules.core.feature.jokes.api.JokeGenerator
import com.louiscad.poc.rolemodules.core.feature.jokes.api.models.JokeId
import com.louiscad.poc.rolemodules.core.feature.jokes.api.models.TextJokeContent
import com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal.JokesDatabase
import com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal.create
import com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal.models.JokeEntity
import com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal.models.TextJokeLineEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.time.Clock
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class JokeGeneratorPocImpl(databaseNameOrPath: String) : JokeGenerator() {

    private val db = JokesDatabase.create(RoomDatabaseConfig(databaseNameOrPath))

    override val jokesGenerationHistory: Flow<List<HistoricalJokeEntry>> = db.dao.jokes.map {
        it.map { (joke, jokeLines) -> joke.toHistoricalEntry(jokeLines) }
    }

    override suspend fun getRandomJoke(): Joke = Joke(
        id = getNextJokeId(),
        content = defaultJokes.random()
    ).also { newJoke ->
        db.withWriteTransaction {
            val timestampMillis = Clock.System.now().toEpochMilliseconds()
            db.dao.insertJoke(JokeEntity(newJoke.id, generatedAt = timestampMillis))
            db.dao.insertLines(newJoke.content.chunks.mapIndexed { index, line ->
                TextJokeLineEntity(
                    jokeId = newJoke.id,
                    index = index,
                    text = line
                )
            })
        }
    }

    override suspend fun getJokeWithPrompt(plainTextPrompt: String): Joke {
        TODO()
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun getNextJokeId(): JokeId = JokeId(Uuid.generateV7().toHexDashString())
}

private fun JokeEntity.toHistoricalEntry(lines: List<TextJokeLineEntity>): HistoricalJokeEntry {
    return HistoricalJokeEntry(
        id = id,
        generatedAt = Instant.fromEpochMilliseconds(generatedAt),
        content = TextJokeContent(chunks = lines.map { it.text })
    )
}

private val defaultJokes = buildList {
    this += TextJokeContent(listOf("Le soleil se lève sans poser les pieds par terre."))
    this += TextJokeContent(listOf("Qu'est-ce qui est jaune et qui attend ?", "Jonathan"))
    this += TextJokeContent(listOf("Comment appelle-t-on un cochon avec des ailes ?", "Un aéro-porc."))
    this += TextJokeContent(listOf("Quel acteur a la main verte ?", "Jean Dujardin"))
    this += TextJokeContent(listOf("Une femme arrive chez un barbier. Il dit quoi ?", "Feur !"))
    this += TextJokeContent(listOf("Jacques a sa langue qui fourche. Il dit quoi ?", "Quoicoubeh !"))
}
