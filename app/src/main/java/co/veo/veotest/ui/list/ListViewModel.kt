package co.veo.veotest.ui.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import co.veo.veotest.models.Movie
import co.veo.veotest.network.MovieRepository
import co.veo.veotest.network.Resource
import co.veo.veotest.ui.list.ListState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ListState {
    data class Success(val movies: List<Movie>) : ListState()
    data class Error(val error: String) : ListState()
    data object Loading : ListState()
}

@HiltViewModel
class ListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val mutableMovies = mutableStateOf<ListState>(Loading)
    val movies: State<ListState> = mutableMovies

    init {
        loadMovies()
    }

    fun loadMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.IO).launch {
                when (val result = movieRepository.getTrendingMovies()) {
                    is Resource.Success -> mutableMovies.value = ListState.Success(result.data)
                    is Resource.Error -> mutableMovies.value = ListState.Error(result.message)
                }
            }
        }
    }
}