package com.dicoding.ui.component

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.dicoding.ui.theme.SubmissionComposeTheme

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun Web(modifier: Modifier = Modifier, url: String, additionalConfig: WebView.() -> Unit = {}) {
    AndroidView(
        modifier = modifier
            .testTag("WebView")
            .fillMaxSize(),
        factory = {
            WebView(it).apply {
                webViewClient = android.webkit.WebViewClient()
            }.also(additionalConfig)
        },
        update = {
            it.loadUrl(url)
        },

        )
}

@Preview
@Composable
private fun WebPreview() {
    SubmissionComposeTheme {
        Web(modifier = Modifier, url = "https://id.wikipedia.org/wiki/Indonesia")
    }
}