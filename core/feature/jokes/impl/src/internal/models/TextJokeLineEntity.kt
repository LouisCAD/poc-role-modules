package com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal.models

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.ForeignKey.Companion.CASCADE
import com.louiscad.poc.rolemodules.core.feature.jokes.api.models.JokeId

@Entity(
    tableName = "jokes_test_lines",
    primaryKeys = ["jokeId", "index"],
    foreignKeys = [
        ForeignKey(
            entity = JokeEntity::class,
            parentColumns = ["id"],
            childColumns = ["jokeId"],
            onDelete = CASCADE,
        ),
    ]
)
internal data class TextJokeLineEntity(
    val jokeId: JokeId,
    val index: Int,
    val text: String,
)
