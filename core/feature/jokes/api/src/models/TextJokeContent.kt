package com.louiscad.poc.rolemodules.core.feature.jokes.api.models

import androidx.compose.runtime.Immutable

@Immutable
data class TextJokeContent(
    val chunks: List<String>,
)
