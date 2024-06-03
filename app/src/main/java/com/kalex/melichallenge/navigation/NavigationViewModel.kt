package com.kalex.melichallenge.navigation

import androidx.lifecycle.ViewModel
import com.kalex.melichallenge.search.model.dto.Result

/**
 * This ViewModel provides methods to set and retrieve the product detail.
 * is a way to pass arguments between screens, this ViewModel should be scoped to the activity.
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