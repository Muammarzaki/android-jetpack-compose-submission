package com.dicoding.ui.author

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.R
import com.dicoding.ui.component.HeaderWithBack
import com.dicoding.ui.theme.SubmissionComposeTheme

@Composable
fun AuthorScreen(modifier: Modifier = Modifier, navigation: NavHostController) {
    Scaffold(topBar = {
        HeaderWithBack(title = "About Me", onBackClick = { navigation.navigateUp() })
    },
        content = { innerPadding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .offset(y = LocalConfiguration.current.screenHeightDp.dp / 9),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(
                        R.drawable.foto_muammar_zaki
                    ),
                    contentDescription = stringResource(id = R.string.image),
                    modifier = modifier
                        .size(200.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                NameAndEmailComponent(
                    modifier = modifier
                        .padding(top = 16.dp)

                )
            }
        }
    )
}

@Composable
fun NameAndEmailComponent(modifier: Modifier = Modifier) {
    Text(
        stringResource(R.string.author_name),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 8.dp)
    )
    Text(
        stringResource(R.string.author_email),
        fontWeight = FontWeight.SemiBold,
    )
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
private fun AuthorScreenPreview() {
    SubmissionComposeTheme {
        Surface(Modifier.fillMaxSize()) {
            AuthorScreen(modifier = Modifier, navigation = rememberNavController())
        }
    }
}