package com.dicoding.ui.component

import android.content.Context
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import com.dicoding.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FlagCardComponentKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        composeTestRule.setContent {
            FlagCard(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.indonesia),
                countryName = "Indonesia"
            )
        }
    }

    @Test
    fun should_has_two_component_show() {
        composeTestRule.onNodeWithTag("FlagCard")
            .assertExists()
            .apply {
                onChildAt(0)
                    .assert(hasContentDescription(context.resources.getString(R.string.flag_description)))
                onChildAt(1)
                    .assert(hasText("Indonesia"))
            }
            .onChildren()
            .assertCountEquals(2)
    }

    @Test
    fun must_not_clickable_component() {
        composeTestRule.onNodeWithTag("FlagCard")
            .assertHasNoClickAction()
    }
}