package com.dicoding.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.DataSource
import com.dicoding.R
import com.dicoding.ui.theme.SubmissionComposeTheme

@Composable
fun FlagCard(modifier: Modifier = Modifier, painter: Painter, countryName: String) {
    Card(
        shape = MaterialTheme.shapes.medium, modifier = Modifier
            .testTag("FlagCard")
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Image(
                modifier = modifier
                    .padding(start = 2.dp)
                    .size(80.dp),
                painter = painter,
                contentDescription = stringResource(R.string.flag_description)
            )
            Column(
                modifier = modifier
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    countryName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FlagCardPreview() {
    SubmissionComposeTheme {
        Surface {
            FlagCard(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.indonesia),
                countryName = "Indonesia"
            )
        }
    }
}

@Preview
@Composable
private fun FlagComponentListPreview() {
    SubmissionComposeTheme {
        Surface {
            LazyColumn {
                items(DataSource.countries) {
                    FlagCard(
                        modifier = Modifier,
                        painter = painterResource(id = it.countryFlagId),
                        countryName = it.name
                    )
                }
            }
        }
    }
}