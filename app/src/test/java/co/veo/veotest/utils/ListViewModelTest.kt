package co.veo.veotest.utils

import co.veo.veotest.models.Movie
import org.junit.Assert
import org.junit.Test

class UtilsTest {

    private val movieSorter = MovieSorter()

    @Test
    fun `given an empty movie list return an empty movie list`() {
        val unsortedMovies = emptyList<Movie>()
        val sortedMovies = movieSorter.sortMovies(unsortedMovies)
        assert(sortedMovies.isEmpty())
    }

    @Test
    fun `given a lit with two movies sort them by title`() {
        val unsortedMovies = listOf(
            Movie(id = 0, title = "BBB", imageUrl = ""),
            Movie(id = 1, title = "AAA", imageUrl = "")
        )
        val sortedMovies = movieSorter.sortMovies(unsortedMovies)
        Assert.assertEquals(sortedMovies.first().id, 1)
    }

    @Test
    fun `given a lit with five movies sort them by title`() {
        val unsortedMovies = listOf(
            Movie(id = 0, title = "BBB", imageUrl = ""),
            Movie(id = 1, title = "DDD", imageUrl = ""),
            Movie(id = 2, title = "CCC", imageUrl = ""),
            Movie(id = 3, title = "AAA", imageUrl = ""),
            Movie(id = 4, title = "EEE", imageUrl = "")
        )
        val sortedMovies = movieSorter.sortMovies(unsortedMovies)
        Assert.assertEquals(sortedMovies.first().id, 3)
        Assert.assertEquals(sortedMovies[1].id, 0)
        Assert.assertEquals(sortedMovies[2].id, 2)
        Assert.assertEquals(sortedMovies[3].id, 1)
        Assert.assertEquals(sortedMovies.last().id, 4)
    }
}