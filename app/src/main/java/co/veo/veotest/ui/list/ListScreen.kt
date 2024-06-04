package co.veo.veotest.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.veo.veotest.models.Movie
import co.veo.veotest.ui.common.ErrorScreen
import co.veo.veotest.ui.common.LoadingScreen
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun ListScreen(
    listState: State<ListState>,
    onMovieClicked: (movie: Movie) -> Unit
) {
    when (val state = listState.value) {
        is ListState.Loading -> LoadingScreen()
        is ListState.Success -> ListSuccessScreen(
            movies = state.movies,
            onMovieClicked = onMovieClicked
        )
        is ListState.Error -> ErrorScreen(state.error)
    }
}

@Composable
fun ListSuccessScreen(
    movies: List<Movie>,
    onMovieClicked: (movie: Movie) -> Unit
) {
    LazyColumn {
        items(movies) { movie ->
            MovieItem(
                movie = movie,
                onMovieClicked = onMovieClicked
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(
    movie: Movie,
    onMovieClicked: (movie: Movie) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onMovieClicked.invoke(movie)
            }
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
    ListSuccessScreen(
        movies = previewData,
        onMovieClicked = {}
    )
}

private val previewData =
    listOf(
        Movie(
            id = 0,
            title = "2001 A space odyssey",
            imageUrl = "https://image.tmdb.org/t/p/w300/ve72VxNqjGM69Uky4WTo2bK6rfq.jpg"
        ),
        Movie(
            id = 1,
            title = "Blade Runner",
            imageUrl = "https://image.tmdb.org/t/p/w300/63N9uy8nd9j7Eog2axPQ8lbr3Wj.jpg"
        ),
        Movie(
            id = 2,
            title = "Dune 1",
            imageUrl = "https://image.tmdb.org/t/p/w300/d5NXSklXo0qyIYkgV94XAgMIckC.jpg"
        ),
        Movie(
            id = 3,
            title = "Dune 2",
            imageUrl = "https://image.tmdb.org/t/p/w300/czembW0Rk1Ke7lCJGahbOhdCuhV.jpg"
        )
    )
