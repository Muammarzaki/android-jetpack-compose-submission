package com.dicoding.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.R
import com.dicoding.ui.theme.SubmissionComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderWithBack(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit,
) {
    Column(modifier = modifier.testTag("HeaderWithBack")) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            }
        )
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.Black,
            modifier = modifier.alpha(0.6F)
        )
    }
}

@Preview
@Composable
private fun HeaderWithBackPreview() {
    SubmissionComposeTheme {
        HeaderWithBack(title = "Test", onBackClick = {})
    }
}