package com.kalex.melichallenge.navigation

import com.kalex.melichallenge.mocks.mockResult
import com.kalex.melichallenge.search.model.dto.Result
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * @author kevin Alexander Soto on 6/2/2024
 */
class NavigationViewModelTest {


    private lateinit var viewModel: NavigationViewModel

    @Before
    fun setUp() {
        viewModel = NavigationViewModel()
    }

    @Test
    fun `should set current product and return it`() {
        viewModel.setProductDetail(mockResult)

        assertTrue(viewModel.getActualProduct() is Result)
    }

    @Test
    fun `should set not set product and return null`() {
        assertTrue(viewModel.getActualProduct() == null)
    }
}