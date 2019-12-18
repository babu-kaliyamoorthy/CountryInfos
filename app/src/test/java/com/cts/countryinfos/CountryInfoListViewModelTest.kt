package com.cts.countryinfos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cts.countryinfos.model.Country
import com.cts.countryinfos.model.Info
import com.cts.countryinfos.network.CountryInfoRemoteDataSource
import com.cts.countryinfos.repository.ApiResponse
import com.cts.countryinfos.repository.CountryInfoRepository
import com.cts.countryinfos.ui.CountryInfoListViewModel
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

/**
 * Created by Babu Kaliyamoorthy on 17/12/19.
 */
@RunWith(JUnit4::class)
class CountryInfoListViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var countryInfoListViewModel: CountryInfoListViewModel

    @Mock
    lateinit var countryInfoRemoteDataSource: CountryInfoRemoteDataSource

    @Mock
    lateinit var countryInfoRepository: CountryInfoRepository

    @Mock
    private lateinit var isFetchInProgressObserver: Observer<Boolean>
    private lateinit var countryObserver: Observer<Country>
    private lateinit var countryInfoList: ArrayList<Info>

    @Before
    fun setUp() {
        /*
        we will initialize all mocked elements with this function.
         */
        MockitoAnnotations.initMocks(this)
        initializeObservers()

        countryInfoListViewModel = CountryInfoListViewModel(countryInfoRepository).apply {
            isFetchInProgress.observeForever(isFetchInProgressObserver)
            country.observeForever(countryObserver)
        }
    }


    @Test
    fun countryInfoParsingSuccessResponse() = testCoroutineRule.runBlockingTest {
        //Given
        val apiResponse = createCountryInfoData()

        runBlocking { whenever(countryInfoRemoteDataSource.getCountryInfos()) }.thenReturn(
            apiResponse
        )

        //when
        countryInfoListViewModel.fetchCountryInfoList()


        //then
        Assert.assertNotNull(countryInfoListViewModel.isFetchInProgress.value)
        Assert.assertNotNull(countryInfoListViewModel.country.value)
        Assert.assertNotNull(countryInfoListViewModel.country.value?.rows)
        Assert.assertTrue(countryInfoListViewModel.country.value?.rows?.size == 3)
    }

    @Test
    fun issuesParsingFailedResponse() =
        testCoroutineRule.runBlockingTest {
            //Given
            val error = Error()
            whenever(countryInfoRemoteDataSource.getCountryInfos()).thenThrow(error)

            //when
            countryInfoListViewModel.fetchCountryInfoList()

            //then
            Assert.assertNotNull(countryInfoListViewModel.isFetchInProgress.value)
            Assert.assertNull(countryInfoListViewModel.country.value)
            Assert.assertNull(countryInfoListViewModel.country.value?.rows)
            Assert.assertTrue(countryInfoListViewModel.country.value?.rows?.size == 0)
        }


    private fun initializeObservers() {
        isFetchInProgressObserver = mock(Observer::class.java) as Observer<Boolean>
        countryObserver = mock(Observer::class.java) as Observer<Country>
    }

    private fun createMockCountryInfoList(): ArrayList<Info> {
        countryInfoList = ArrayList()
        countryInfoList.add(
            Info(
                "A moose is a common sight in Canada. Tall and majestic, they represent many of the values which Canadians imagine that they possess. They grow up to 2.7 metres long and can weigh over 700 kg. They swim at 10 km/h. Moose antlers weigh roughly 20 kg. The plural of moose is actually 'meese', despite what most dictionaries, encyclopedias, and experts will tell you.",
                "http://caroldeckerwildlifeartstudio.net/wp-content/uploads/2011/04/IMG_2418%20majestic%20moose%201%20copy%20(Small)-96x96.jpg",
                "Meese"
            )
        )

        countryInfoList.add(
            Info(
                "A moose is a common sight in Canada. Tall and majestic, they represent many of the values which Canadians imagine that they possess. They grow up to 2.7 metres long and can weigh over 700 kg. They swim at 10 km/h. Moose antlers weigh roughly 20 kg. The plural of moose is actually 'meese', despite what most dictionaries, encyclopedias, and experts will tell you.",
                "http://caroldeckerwildlifeartstudio.net/wp-content/uploads/2011/04/IMG_2418%20majestic%20moose%201%20copy%20(Small)-96x96.jpg",
                "Meese"
            )
        )
        countryInfoList.add(
            Info(
                "A moose is a common sight in Canada. Tall and majestic, they represent many of the values which Canadians imagine that they possess. They grow up to 2.7 metres long and can weigh over 700 kg. They swim at 10 km/h. Moose antlers weigh roughly 20 kg. The plural of moose is actually 'meese', despite what most dictionaries, encyclopedias, and experts will tell you.",
                "http://caroldeckerwildlifeartstudio.net/wp-content/uploads/2011/04/IMG_2418%20majestic%20moose%201%20copy%20(Small)-96x96.jpg",
                "Meese"
            )
        )

        return countryInfoList
    }


    private fun createCountryInfoData(): ApiResponse<Country> {
        return ApiResponse.SuccessResponse(Country(createMockCountryInfoList(), "About Canada"))
    }

}
