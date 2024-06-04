package co.veo.veotest.ui.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import co.veo.veotest.models.Movie
import co.veo.veotest.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val mutableMovies = mutableStateOf<List<Movie>>(emptyList())
    val movies: State<List<Movie>> = mutableMovies

    init {
        CoroutineScope(Dispatchers.IO).launch {
            loadMovies()
        }
    }

    fun loadMovies() {
        val response = apiService.getTrending().execute()
        val body = response.body()
        if (response.isSuccessful && body != null)
            mutableMovies.value = body.results.map { movieResponse ->
                val title = movieResponse.originalTitle ?: (movieResponse.title ?: (movieResponse.name ?: "Unnamed Movie"))
                Movie(
                    id = movieResponse.id,
                    title = title,
                    imageUrl = "https://image.tmdb.org/t/p/w300/${movieResponse.posterPath}"
                )
            }
    }
}