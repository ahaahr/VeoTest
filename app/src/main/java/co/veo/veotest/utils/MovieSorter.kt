package co.veo.veotest.utils

import co.veo.veotest.models.Movie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class MovieSorter {

    fun sortMovies(movies: List<Movie>): List<Movie> = movies.sortedBy { movie ->
        movie.title
    }
}

@Module
@InstallIn(SingletonComponent::class)
class MovieSorterModule {

    @Provides
    @Singleton
    fun provideMovieSorter(): MovieSorter = MovieSorter()
}