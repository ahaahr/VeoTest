package co.veo.veotest.ui.list

import androidx.lifecycle.ViewModel
import co.veo.veotest.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor() : ViewModel() {

    fun getMovies(): List<Movie> = listOf(
        Movie("2001: A Space Odyssey"),
        Movie("Blade Runner"),
        Movie("Dune"),
        Movie("Dune2")
    )
}