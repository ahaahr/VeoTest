package co.veo.veotest.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.veo.veotest.models.MovieDetails
import co.veo.veotest.ui.common.ErrorScreen
import co.veo.veotest.ui.common.LoadingScreen
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun DetailsScreen(
    detailsState: State<DetailsState>
) {
    when (val state = detailsState.value) {
        DetailsState.Loading -> LoadingScreen()
        is DetailsState.Success -> DetailsSuccessScreen(state.movieDetails)
        is DetailsState.Error -> ErrorScreen(state.error)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsSuccessScreen(movieDetails: MovieDetails) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
    ) {
        Text(
            text = movieDetails.title,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp),
            fontSize = 20.sp
        )
        GlideImage(
            model = movieDetails.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp)
        )
        Text(
            text = movieDetails.overview,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(
            text = "Runtime: ${movieDetails.runtime}",
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(
            text = "Release date: ${movieDetails.releaseDate}",
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}