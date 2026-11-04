package com.dicoding.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.R
import com.dicoding.ui.component.FlagCard
import com.dicoding.ui.component.SwipeDelete
import com.dicoding.ui.theme.SubmissionComposeTheme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigation: NavHostController,
    viewModel: HomeViewModel = viewModel()
) {
    val searchState = rememberSearchState()
    val countries by viewModel.countries.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        if (!searchState.searchAble) {
                            Text(
                                text = "NEGARA",
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.5.sp
                            )
                        } else {
                            OutlinedTextField(
                                singleLine = true,
                                value = searchState.searchQuery,
                                onValueChange = {
                                    searchState.searchQuery = it
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Clear,
                                        contentDescription = stringResource(id = R.string.clear),
                                        modifier = modifier.clickable {
                                            if (searchState.searchQuery.isEmpty())
                                                searchState.searchAble = false
                                            searchState.searchQuery = ""
                                        }
                                    )
                                },
                                modifier = modifier
                                    .testTag("SearchField")
                                    .height(intrinsicSize = IntrinsicSize.Min)
                                    .fillMaxWidth(0.9f),
                            )
                        }
                    },
                    actions = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier
                        ) {
                            if (!searchState.searchAble) {
                                IconButton(modifier = modifier
                                    .padding(16.dp)
                                    .size(42.dp)
                                    .testTag("SearchButton"),
                                    onClick = {
                                        searchState.searchAble = !searchState.searchAble
                                    }) {
                                    Icon(
                                        modifier = modifier,
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = stringResource(id = R.string.search)
                                    )
                                }
                            }
                            IconButton(
                                modifier = modifier
                                    .padding(end = 16.dp)
                                    .size(42.dp),
                                onClick = { navigation.navigate("about") }
                            ) {
                                Icon(
                                    modifier = modifier.fillMaxSize(),
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = stringResource(id = R.string.about_page)
                                )
                            }
                        }
                    }
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    color = Color.Black,
                    modifier = modifier.alpha(0.6F)
                )
            }
        },
        floatingActionButton = {
            IconButton(
                modifier = modifier
                    .testTag("FloatingActionButton")
                    .clip(RoundedCornerShape(percent = 20))
                    .background(MaterialTheme.colorScheme.primary),
                onClick = {
                    navigation.navigate("new")
                }) {
                Icon(
                    modifier = modifier.size(30.dp),
                    tint = Color.White,
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.add)
                )
            }
        }
    ) { innerPadding ->

        LazyColumn(
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .testTag("ContentColumn")
                .padding(top = 16.dp)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            items(
                countries.filter { it.name.contains(searchState.searchQuery, true) },
                key = { it.name }) {
                SwipeDelete(onDeleted = {
                    viewModel.deleteWithIndex(it)
                }) {
                    FlagCard(
                        painter = painterResource(it.countryFlagId),
                        countryName = it.name,
                        modifier = modifier
                            .clickable {
                                val url = URLEncoder.encode(
                                    it.detailWikiLink.toString(),
                                    StandardCharsets.UTF_8.toString()
                                )
                                navigation.navigate("detail/${url}")
                            }
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true, device = "id:pixel_5", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreviewDark() {
    SubmissionComposeTheme {
        HomeScreen(
            modifier = Modifier,
            navigation = rememberNavController(),
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_5", uiMode = Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun HomeScreenPreviewLight() {
    SubmissionComposeTheme {
        HomeScreen(
            modifier = Modifier,
            navigation = rememberNavController(),
        )
    }
}