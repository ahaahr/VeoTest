package co.veo.veotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.veo.veotest.ui.DetailsDest
import co.veo.veotest.ui.ListDest
import co.veo.veotest.ui.details.DetailsScreen
import co.veo.veotest.ui.list.ListScreen
import co.veo.veotest.ui.list.ListViewModel
import co.veo.veotest.ui.theme.VeoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val listViewModel by viewModels<ListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VeoTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = ListDest) {
                    composable<ListDest> { ListScreen(listViewModel) }
                    composable<DetailsDest> { DetailsScreen() }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VeoTheme {
        Greeting("Android")
    }
}