package com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
import androidx.sqlite.SQLiteDriver
import com.louiscad.poc.rolemodules.core.common.coroutines.IO
import com.louiscad.poc.rolemodules.core.common.room.RoomDatabaseConfig
import com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal.models.JokeEntity
import com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal.models.TextJokeLineEntity
import kotlinx.coroutines.Dispatchers

internal fun JokesDatabase.Companion.create(config: RoomDatabaseConfig): JokesDatabase {
    return config.toDatabaseBuilder<JokesDatabase>()
        .setDriver(sqliteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

@Database(
    entities = [
        JokeEntity::class,
        TextJokeLineEntity::class,
    ],
    version = 1
)
@ConstructedBy(JokeDatabaseConstructor::class)
internal abstract class JokesDatabase : RoomDatabase() {
    companion object;
    abstract val dao: JokeDao
}

@Suppress("KotlinNoActualForExpect") // The Room compiler generates the `actual` implementations.
internal expect object JokeDatabaseConstructor : RoomDatabaseConstructor<JokesDatabase> {
    override fun initialize(): JokesDatabase
}

internal expect fun sqliteDriver(): SQLiteDriver
