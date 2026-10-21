package com.louiscad.poc.rolemodules.core.feature.jokes.api.models

import androidx.compose.runtime.Immutable

@Immutable
data class Joke(
    val id: JokeId,
    val content: TextJokeContent,
)
