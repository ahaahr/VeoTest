package co.veo.veotest.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorScreen(error: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = error,
            color = Color.Red,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
