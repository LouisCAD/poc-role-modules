package com.louiscad.poc.rolemodules.core.feature.jokes.api

import kotlin.time.Instant

data class HistoricalJokeEntry(
    val id: JokeId,
    val generatedAt: Instant,
    val content: TextJokeContent,
)
