package com.louiscad.poc.rolemodules.core.feature.jokes.api

import androidx.compose.runtime.Immutable

@Immutable
data class TextJokeContent(
    val chunks: List<String>,
)
