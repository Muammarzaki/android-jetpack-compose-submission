package com.dicoding.ui.component

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SwipeDeleteComponentKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var onDeletedMock: () -> Unit

    @Before
    fun setUp() {
        onDeletedMock = mockk<() -> Unit>(relaxed = true)

        composeTestRule.setContent {
            SwipeDelete(onDeleted = onDeletedMock) {
                for (i in 1..10)
                    Text("Indonesia")
            }
        }
    }

    @Test
    fun onSwipe_should_call_onDeleted_when_completely_swiped() {
        composeTestRule.onNodeWithTag("SwipeDelete")
            .performTouchInput {
                swipeRight(
                    startX = 0f,
                    endX = 300f,
                )
            }

        composeTestRule.waitForIdle()

        verify(exactly = 1) {
            onDeletedMock()
        }
    }

    @Test
    fun onSwipe_not_should_call_onDeleted_when_completely_swiped() {
        composeTestRule.onNodeWithTag("SwipeDelete")
            .performTouchInput {
                swipeRight(
                    startX = 0f,
                    endX = 250f,
                )
            }

        composeTestRule.waitForIdle()

        verify(exactly = 0) {
            onDeletedMock()
        }
    }

    @Test
    fun be_able_to_slot_base_other_component() {
        composeTestRule
            .onNodeWithTag("SwipeDelete")
            .onChildren()
            .assertCountEquals(10)
    }
}