package com.dicoding.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class SearchState(initialSearchQuery: String = "") {
    var searchAble by mutableStateOf(false)
    var searchQuery by mutableStateOf(initialSearchQuery)
}

@Composable
fun rememberSearchState(initialSearchQuery: String = ""): SearchState =
    remember(initialSearchQuery) {
        SearchState(initialSearchQuery)
    }
