package com.kalex.melichallenge.navigation

import androidx.lifecycle.ViewModel
import com.kalex.melichallenge.search.model.dto.Result

/**
 * @author kevin Alexander Soto on 6/2/2024
 * **/
class NavigationViewModel(): ViewModel() {

    private lateinit var actualProduct : Result
    fun setProductDetail(product: Result){
        actualProduct = product
    }
    fun getActualProduct(): Result? {
        return if (::actualProduct.isInitialized) actualProduct else null
    }

}