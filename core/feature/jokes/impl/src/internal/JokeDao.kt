package com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal.models.JokeEntity
import com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal.models.TextJokeLineEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface JokeDao {
    @get:Query(
        "SELECT * FROM jokes " +
        "JOIN jokes_test_lines ON jokes.id = jokes_test_lines.jokeId " +
        "ORDER BY jokes.id, jokes_test_lines.`index`"
    )
    val jokes: Flow<Map<JokeEntity, List<TextJokeLineEntity>>>

    @Insert
    suspend fun insertJoke(joke: JokeEntity)

    @Insert
    suspend fun insertLines(lines: List<TextJokeLineEntity>)
}
