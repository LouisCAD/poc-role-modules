package com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal.models

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.louiscad.poc.rolemodules.core.feature.jokes.api.models.JokeId

@Entity(tableName = "jokes")
internal data class JokeEntity(
    @PrimaryKey
    val id: JokeId,
    @ColumnInfo(name = "generation_timestamp_millis")
    val generatedAt: Long,
)
