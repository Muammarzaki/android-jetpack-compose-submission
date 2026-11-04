package com.dicoding.ui.create

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.dicoding.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreateNewScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CreateNewScreen(navigation = rememberNavController())
        }
    }

    @Test
    fun should_have_header_with_back() {
        composeTestRule.onNodeWithTag("HeaderWithBack").assertExists()
    }

    @Test
    fun should_have_two_outlined_text_field() {
        composeTestRule
            .onNodeWithTag("nameInput").assertExists()
            .performTextInput("Indonesia")
        composeTestRule
            .onNodeWithTag("linkInput").assertExists()
            .performTextInput("https://id.wikipedia.org/wiki/Indonesia")
    }

    @Test
    fun should_have_save_button_and_clickable() {
        composeTestRule
            .onNodeWithTag("saveButton")
            .assertExists()
            .assertHasClickAction()
    }

    @Test
    fun should_have_country_flags_pager() {
        composeTestRule
            .onNodeWithTag("CountryFlagsPager")
            .assertExists()
            .performTouchInput {
                swipeRight()
                swipeRight()
                swipeLeft()
            }
            .assertIsDisplayed()
    }

    @Test
    fun should_have_next_and_back_button() {
        composeTestRule
            .also {
                it.onNodeWithContentDescription(context.resources.getString(R.string.next))
                    .assertHasClickAction()

                it.onNodeWithContentDescription(context.resources.getString(R.string.back))
                    .assertHasClickAction()
            }
    }


}