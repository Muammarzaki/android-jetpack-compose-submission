package com.dicoding.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.ui.component.HeaderWithBack
import com.dicoding.ui.component.Web
import com.dicoding.ui.theme.SubmissionComposeTheme

@Composable
fun DetailScreen(modifier: Modifier = Modifier, url: String, navigation: NavHostController) {
    Scaffold(topBar = {
        HeaderWithBack(
            title = "Detail Information",
            onBackClick = { navigation.navigateUp() })
    }) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
        ) {
            Web(
                modifier = modifier,
                url = url
            )
        }
    }

}

@Preview
@Composable
private fun DetailScreenPreview() {
    SubmissionComposeTheme {
        Surface {
            DetailScreen(
                modifier = Modifier,
                url = "https://id.wikipedia.org/wiki/Indonesia",
                navigation = rememberNavController()
            )
        }
    }
}