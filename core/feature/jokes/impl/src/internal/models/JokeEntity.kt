package com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal.models

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.louiscad.poc.rolemodules.core.feature.jokes.api.JokeId

@Entity(tableName = "jokes")
internal data class JokeEntity(
    @PrimaryKey
    val id: JokeId,
)
