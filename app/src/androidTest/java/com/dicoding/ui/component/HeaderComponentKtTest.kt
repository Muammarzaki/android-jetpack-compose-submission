package com.dicoding.ui.component

import android.content.Context
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.dicoding.R
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeaderComponentKtTest {

    private lateinit var context: Context

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var onBackClickMock: () -> Unit

    @Before
    fun setUp() {
        onBackClickMock = mockk<() -> Unit>(relaxed = true)
        context = InstrumentationRegistry.getInstrumentation().targetContext

        composeTestRule.setContent {
            HeaderWithBack(title = "Test", onBackClick = onBackClickMock)
        }
    }

    @Test
    fun should_have_clickable_back_button() {
        composeTestRule.onNodeWithContentDescription(context.resources.getString(R.string.back))
            .assertHasClickAction()
            .performClick()

        verify(exactly = 1) {
            onBackClickMock()
        }
    }
}