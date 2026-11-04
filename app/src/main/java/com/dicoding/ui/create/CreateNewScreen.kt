package com.dicoding.ui.create

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.Country
import com.dicoding.R
import com.dicoding.ui.component.HeaderWithBack
import com.dicoding.ui.home.HomeViewModel
import com.dicoding.ui.theme.SubmissionComposeTheme
import kotlinx.coroutines.launch

@Composable
fun CreateNewScreen(
    modifier: Modifier = Modifier,
    navigation: NavHostController,
    homeViewModel: HomeViewModel = viewModel()
) {
    var countryName by remember { mutableStateOf("") }
    var countryWikiLink by remember { mutableStateOf("") }
    val filteredFlags by homeViewModel.listFlagsIds.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState { filteredFlags.size }

    Scaffold(topBar = {
        HeaderWithBack(title = "Add New", onBackClick = { navigation.navigateUp() })
    }) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = modifier.padding(top = 50.dp))
            Box(
                modifier = modifier.height(200.dp)
            ) {

                HorizontalPager(
                    state = pagerState,
                    modifier = modifier
                        .testTag("CountryFlagsPager")
                        .align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically
                ) { page ->
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(filteredFlags[page]),
                            contentDescription = stringResource(id = R.string.image),
                            modifier = modifier
                                .size(200.dp)
                        )
                    }
                }
                if (pagerState.currentPage != 0) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = stringResource(R.string.back),
                        modifier = modifier
                            .align(Alignment.CenterStart)
                            .scale(2f)
                            .fillMaxHeight()
                            .clickable {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            }
                    )
                }
                if (pagerState.currentPage != filteredFlags.size - 1) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.next),
                        modifier = modifier
                            .align(Alignment.CenterEnd)
                            .scale(2f)
                            .fillMaxHeight()
                            .clickable {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            }
                    )
                }
            }
            Spacer(modifier = modifier.padding(16.dp))
            OutlinedTextField(
                modifier = modifier
                    .testTag("nameInput"),
                value = countryName,
                onValueChange = {
                    countryName = it
                },
                label = { Text(text = "Country Name") }
            )

            Spacer(modifier = modifier.padding(16.dp))
            OutlinedTextField(
                modifier = modifier
                    .testTag("linkInput"),
                value = countryWikiLink,
                onValueChange = {
                    countryWikiLink = it
                },
                label = { Text(text = "Country Wiki URL") }
            )

            Spacer(modifier = modifier.padding(top = 40.dp))
            Button(
                onClick = {
                    homeViewModel.add(
                        Country(
                            name = countryName,
                            countryFlagId = filteredFlags[pagerState.currentPage],
                            detailWikiLink = Uri.parse(countryWikiLink)
                        )
                    )

                    countryWikiLink = ""
                    countryName = ""
                },
                modifier = modifier
                    .testTag("saveButton")
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "SAVE",
                    modifier = modifier.padding(5.dp)
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CreateNewScreenPreview() {
    SubmissionComposeTheme {
        Surface {
            CreateNewScreen(navigation = rememberNavController())
        }
    }
}