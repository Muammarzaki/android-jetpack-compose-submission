package com.dicoding.ui.home

import app.cash.turbine.test
import com.dicoding.DataSource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HomeViewModelTest {
    private var homeViewModel: HomeViewModel? = null

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel()
    }

    @Test
    fun `should get current countries when called`() = runTest {
        homeViewModel?.countries?.test {
            val currentCountries = awaitItem()
            assertThat(currentCountries).isEqualTo(DataSource.countries)
        }
    }

    @Test
    fun `flagIds should observe change when countries changed`() = runTest {
        val currentLength = homeViewModel?.listFlagsIds?.value?.size
        var emmitCounter = 0
        homeViewModel?.listFlagsIds?.test {
            val currentFlagIds = awaitItem()
            currentLength?.plus(emmitCounter++)?.let {
                assertThat(currentFlagIds).hasSize(it)
            }
        }
        for (i in 1..5)
            homeViewModel?.apply {
                deleteWithIndex(countries.value[0])
            }
    }
}