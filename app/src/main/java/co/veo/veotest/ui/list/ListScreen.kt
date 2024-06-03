package co.veo.veotest.ui.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import co.veo.veotest.Greeting

@Composable
fun ListScreen(
    listViewModel: ListViewModel
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Greeting("Movie list")
    }
}