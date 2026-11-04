package com.dicoding.ui.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.hamcrest.core.StringContains.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class WebViewComponentKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            Web(
                modifier = Modifier,
                url = "https://id.wikipedia.org/wiki/Indonesia",
                additionalConfig = {
                    settings.javaScriptEnabled = true
                })
        }
    }

    @Test
    fun perform_WebView_and_show_the_webs() {
        composeTestRule
            .onNodeWithTag("WebView")
            .assertIsDisplayed()
    }

    @Test
    fun should_contain_text_indonesia() {
        composeTestRule.waitForIdle()
        onWebView()
            .withElement(DriverAtoms.findElement(Locator.TAG_NAME, "html"))
            .check(webMatches(DriverAtoms.getText(), containsString("Indonesia")))

    }
}