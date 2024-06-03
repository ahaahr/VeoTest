package co.veo.veotest.ui.list

import org.junit.Assert
import org.junit.Test

class ListViewModelTest {

    private val listViewModel = ListViewModel()

    @Test
    fun `given the movie list verify that it's length equals 4`() {
        Assert.assertEquals(listViewModel.loadMovies().size, 4)
    }
}