package co.veo.veotest.network

import co.veo.veotest.models.Movie
import co.veo.veotest.models.MovieDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class MovieRepository(private val apiService: ApiService) {

    fun getTrendingMovies(): Resource<List<Movie>> {
        val response = apiService.getTrending().execute()
        val body = response.body()
        if (response.isSuccessful.not() || body == null)
            return Resource.Error("Error loading trending movies")
        return Resource.Success(body.results.map { movieResponse ->
            val title = movieResponse.originalTitle ?: (movieResponse.title ?: (movieResponse.name ?: "Unnamed Movie"))
            Movie(
                id = movieResponse.id,
                title = title,
                imageUrl = "https://image.tmdb.org/t/p/w300/${movieResponse.posterPath}"
            )
        })
    }

    fun getMovieDetails(id: Int): Resource<MovieDetails> {
        val response = apiService.getDetails(movieId = id.toString()).execute()
        val body = response.body()
        if (response.isSuccessful.not() || body == null)
            return Resource.Error("Error loading movie details")
        return body.run {
            Resource.Success(
                MovieDetails(
                    title = originalTitle ?: title ?: name ?: "Unnamed Movie",
                    imageUrl = "https://image.tmdb.org/t/p/w780/${posterPath}",
                    releaseDate = releaseDate,
                    runtime = runtime,
                    overview = overview
                )
            )
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
class MovieRepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        apiService: ApiService
    ): MovieRepository = MovieRepository(
        apiService = apiService
    )
}