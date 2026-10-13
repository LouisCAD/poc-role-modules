package com.louiscad.poc.rolemodules.core.feature.jokes.api

import androidx.compose.runtime.Immutable
import kotlin.time.Instant

@Immutable
data class HistoricalJokeEntry(
    val id: JokeId,
    val generatedAt: Instant,
    val content: TextJokeContent,
)
