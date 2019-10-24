package com.germanofilho.app.feature.home.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.germanofilho.app.data.model.entity.*
import com.germanofilho.app.feature.home.repository.IHomeRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest{
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: HomeViewModel
    private val mockedRepo = mock<IHomeRepository>()
    private val openingHours = OpeningHours(openNow = true)
    private val location = Location(-22.30, -40.22)
    private val geometry = Geometry(location = location)
    private val result = Result(name = "Test", id = "1234", distance = 2.2, icon = "url", rating = 2.2,
        vicinity = "street", openingHours = openingHours, geometry = geometry)
    private val results : List<Result> = listOf(result)
    private val placeResponse = PlaceResponse(results)

    @Before
    fun setup_test(){
        viewModel = HomeViewModel(mockedRepo)
    }

    @Test
    fun given_a_venue_should_load_data() {
        val liveData = MutableLiveData<PlaceResponse>().apply {
            value = placeResponse
        }

        whenever(runBlocking {
            mockedRepo.fetchNearbyPlaces(any(), any())
        })
            .thenReturn(liveData.value)

        viewModel.nearbyPlaces.observeForever {
            assertThat(it).isEqualTo(placeResponse)
        }
    }
}