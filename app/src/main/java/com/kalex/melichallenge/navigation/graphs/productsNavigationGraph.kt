package com.kalex.melichallenge.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kalex.melichallenge.navigation.Router
import com.kalex.melichallenge.products.ProductDetailScreen

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/

fun NavGraphBuilder.productsNavigationGraph(rootNavController: NavHostController) {
    navigation(
        route = Router.PRODUCTS_GRAPH,
        startDestination = Router.PRODUCT_DETAIL_SCREEN
    ) {
        composable(route = Router.PRODUCT_DETAIL_SCREEN) {
            ProductDetailScreen() {
                rootNavController.navigateUp()
            }
        }
    }
}