package co.veo.veotest.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.veo.veotest.models.Movie
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun ListScreen(
    movies: State<List<Movie>>
) {
//    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//        Greeting("Movie list. Movie count: ${movies.value.size}")
//    }
    LazyColumn {
        items(movies.value) { movie ->
            MovieItem(movie)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(movie: Movie) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            text = movie.title,
            modifier = Modifier.align(Alignment.CenterStart),
            fontSize = 18.sp
        )
        GlideImage(
            model = movie.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    ListScreen(previewData)
}

private val previewData = mutableStateOf(
    listOf(
        Movie(
            title = "2001 A space odyssey",
            imageUrl = "https://image.tmdb.org/t/p/w300/ve72VxNqjGM69Uky4WTo2bK6rfq.jpg"
        ),
        Movie(
            title = "Blade Runner",
            imageUrl = "https://image.tmdb.org/t/p/w300/63N9uy8nd9j7Eog2axPQ8lbr3Wj.jpg"
        ),
        Movie(
            title = "Dune 1",
            imageUrl = "https://image.tmdb.org/t/p/w300/d5NXSklXo0qyIYkgV94XAgMIckC.jpg"
        ),
        Movie(
            title = "Dune 2",
            imageUrl = "https://image.tmdb.org/t/p/w300/czembW0Rk1Ke7lCJGahbOhdCuhV.jpg"
        )
    )
)