package co.veo.veotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import co.veo.veotest.ui.DetailsDest
import co.veo.veotest.ui.ListDest
import co.veo.veotest.ui.details.DetailsScreen
import co.veo.veotest.ui.details.DetailsViewModel
import co.veo.veotest.ui.list.ListScreen
import co.veo.veotest.ui.list.ListViewModel
import co.veo.veotest.ui.theme.VeoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val listViewModel by viewModels<ListViewModel>()
    private val detailsViewModel by viewModels<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VeoTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = ListDest) {
                    composable<ListDest> {
                        ListScreen(
                            listState = listViewModel.movies,
                            onMovieClicked = {
                                navController.navigate(route = DetailsDest(it.id))
                            }
                        )
                    }
                    composable<DetailsDest> { backStackEntry ->
                        val detailsDest: DetailsDest = backStackEntry.toRoute()
                        detailsViewModel.setSelectedMovieId(detailsDest.movieId)
                        DetailsScreen(detailsViewModel.movieDetails)
                    }
                }
            }
        }
    }
}
