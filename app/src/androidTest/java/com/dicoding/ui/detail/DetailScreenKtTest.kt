package com.dicoding.ui.detail

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            DetailScreen(
                modifier = Modifier,
                url = "https://id.wikipedia.org/wiki/Indonesia",
                navigation = rememberNavController(),
            )

        }
    }


    @Test
    fun should_contains_header_with_back() {
        composeTestRule
            .onNodeWithTag("HeaderWithBack").assertIsDisplayed()
    }

    @Test
    fun should_contains_web_component() {
        composeTestRule
            .onNodeWithTag("WebView").assertIsDisplayed()
    }
}