package com.kalex.melichallenge.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kalex.melichallenge.navigation.Router
import com.kalex.melichallenge.search.presentation.searchMain.SearchMainScreen
import com.kalex.melichallenge.search.presentation.searchResult.SearchResultScreen

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/

fun NavGraphBuilder.searchNavigationGraph(rootNavController: NavHostController) {
    navigation(
        route = Router.SEARCH_GRAPH,
        startDestination = Router.SEARCH_MAIN_SCREEN
    ) {
        composable(route = Router.SEARCH_MAIN_SCREEN) {
            SearchMainScreen()
        }

        composable(route = Router.SEARCH_RESULT_SCREEN) {
            SearchResultScreen()
        }
    }
}