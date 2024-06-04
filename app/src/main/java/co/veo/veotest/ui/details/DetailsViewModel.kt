package co.veo.veotest.ui.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import co.veo.veotest.models.MovieDetails
import co.veo.veotest.network.MovieRepository
import co.veo.veotest.network.Resource
import co.veo.veotest.ui.details.DetailsState.Error
import co.veo.veotest.ui.details.DetailsState.Loading
import co.veo.veotest.ui.details.DetailsState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DetailsState {
    data class Success(val movieDetails: MovieDetails) : DetailsState()
    data class Error(val error: String) : DetailsState()
    data object Loading : DetailsState()
}

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val mutableMovieDetails: MutableState<DetailsState> = mutableStateOf(Loading)
    val movieDetails: State<DetailsState> = mutableMovieDetails

    fun setSelectedMovieId(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            when (val result = movieRepository.getMovieDetails(id)) {
                is Resource.Success -> mutableMovieDetails.value = Success(result.data)
                is Resource.Error -> mutableMovieDetails.value = Error(result.message)
            }
        }
    }
}