package com.dicoding.ui.author

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.dicoding.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthorScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        composeTestRule.setContent {
            AuthorScreen(modifier = Modifier, navigation = rememberNavController())
        }
    }

    @Test
    fun should_have_header_with_back() {
        composeTestRule.onNodeWithTag("HeaderWithBack").assertExists()
    }

    @Test
    fun should_have_name_and_email_component() {
        composeTestRule
            .also {
                it.onNodeWithText("Muammar Zaki").assertExists()
                it.onNodeWithText("muammarzaki2004@gmail.com").assertExists()
                it.onNodeWithContentDescription(context.resources.getString(R.string.image))
            }
    }

}