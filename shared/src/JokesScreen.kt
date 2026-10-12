package com.louiscad.poc.rolemodules

import Jokes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.louiscad.poc.rolemodules.core.feature.jokes.api.JokeGenerator
import com.louiscad.poc.rolemodules.core.feature.jokes.impl.JokeGeneratorPocImpl

@Composable
fun JokesScreen(modifier: Modifier = Modifier) {
    val jokeGenerator: JokeGenerator = remember { JokeGeneratorPocImpl() }
    Jokes(jokeGenerator, modifier)
}
