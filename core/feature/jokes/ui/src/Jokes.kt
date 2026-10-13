import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.louiscad.poc.rolemodules.core.feature.jokes.api.HistoricalJokeEntry
import com.louiscad.poc.rolemodules.core.feature.jokes.api.JokeGenerator
import kotlinx.coroutines.channels.Channel

@Composable
fun Jokes(
    generator: JokeGenerator,
    modifier: Modifier = Modifier
) {
    val jokesHistory by generator.jokesGenerationHistory.collectAsState(initial = null)
    Column(modifier = modifier) {
        val newJokeRequests = remember { Channel<Unit>() }
        LaunchedEffect(newJokeRequests) {
            @Suppress("UNNAMED_PROPERTY_WITH_IMPLICIT_UNIT_TYPE")
            for (_ in newJokeRequests) generator.getRandomJoke()
        }
        Button(onClick = { newJokeRequests.trySend(Unit) }) {
            Text("New joke")
        }
        LazyColumn {
            val history = jokesHistory ?: return@LazyColumn item { JokesLoading() }
            itemsIndexed(history, key = { _, entry -> entry.id }) { index, entry ->
                if (index != 0) HorizontalDivider()
                JokeHistoryItem(entry)
            }
        }
    }
}

@Composable
internal fun JokesLoading() {
    LinearWavyProgressIndicator()
}

@Composable
internal fun JokeHistoryItem(entry: HistoricalJokeEntry, modifier: Modifier = Modifier) {
    var lastRevealedIndex by remember { mutableIntStateOf(0) }
    val chunks = entry.content.chunks
    Column(
        modifier.clickable(
            enabled = lastRevealedIndex < chunks.lastIndex,
            onClick = { lastRevealedIndex++ },
            onClickLabel = "Reveal part ${lastRevealedIndex + 1}"
        ).padding(8.dp)
    ) {
        chunks.fastForEachIndexed { index, string ->
            val revealed = lastRevealedIndex >= index
            val fontSize = with(LocalDensity.current) { LocalTextStyle.current.fontSize.toDp() }
            Text(
                text = string,
                modifier = if (revealed) Modifier else Modifier.blur(fontSize / 2, edgeTreatment = BlurredEdgeTreatment.Unbounded)
            )
        }
    }
}
