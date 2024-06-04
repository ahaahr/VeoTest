package co.veo.veotest.ui.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import co.veo.veotest.models.MovieDetails
import co.veo.veotest.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val mutableMovieDetails = mutableStateOf(
        MovieDetails("", "", "", "", "")
    )
    val movieDetails: State<MovieDetails> = mutableMovieDetails

    fun setSelectedMovieId(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getDetails(movieId = id.toString()).execute()
            val body = response.body()
            if (response.isSuccessful)
                body?.apply {
                    mutableMovieDetails.value = MovieDetails(
                        title = originalTitle ?: title ?: name ?: "Unnamed Movie",
                        imageUrl = "https://image.tmdb.org/t/p/w780/${posterPath}",
                        releaseDate = releaseDate,
                        runtime = runtime,
                        overview = overview
                    )
                }
        }
    }
}