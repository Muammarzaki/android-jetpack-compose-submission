package com.dicoding.ui.home

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.dicoding.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        composeTestRule.setContent {
            HomeScreen(modifier = Modifier, navigation = rememberNavController())
        }
    }

    @Test
    fun should_have_top_bar_by_default() {
        composeTestRule
            .onNodeWithText("NEGARA")
            .onParent().onChildren().assertCountEquals(3)
    }

    @Test
    fun should_have_search_bar_when_search_button_clicked() {
        composeTestRule
            .onNodeWithTag("SearchButton")
            .performClick()
            .assertIsNotDisplayed()

        composeTestRule
            .onNodeWithTag("SearchField")
            .assertIsDisplayed()
            .onParent().onChildren().assertCountEquals(2)
    }

    @Test
    fun should_have_floating_action_button() {
        composeTestRule
            .onNodeWithTag("FloatingActionButton")
            .assertIsDisplayed()
    }

    @Test
    fun should_have_about_button() {
        composeTestRule
            .onNodeWithContentDescription(context.resources.getString(R.string.about_page))
            .assertIsDisplayed()
    }

    @Test
    fun content_should_be_displayed_and_scrolled() {
        composeTestRule
            .onNodeWithTag("ContentColumn")
            .assertIsDisplayed()
            .assert(hasScrollAction())
    }
}