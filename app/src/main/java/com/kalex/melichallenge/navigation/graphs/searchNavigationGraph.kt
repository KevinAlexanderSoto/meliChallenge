package com.kalex.melichallenge.navigation.graphs

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.kalex.melichallenge.navigation.Router
import com.kalex.melichallenge.search.presentation.searchMain.SearchMainScreen
import com.kalex.melichallenge.search.presentation.searchResult.compose.SearchResultScreen

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/

fun NavGraphBuilder.searchNavigationGraph(rootNavController: NavHostController) {
    navigation(
        route = Router.SEARCH_GRAPH,
        startDestination = Router.SEARCH_MAIN_SCREEN
    ) {
        composable(route = Router.SEARCH_MAIN_SCREEN) {
            SearchMainScreen(
                onSearchProduct = { query ->
                rootNavController.navigate(Router.SEARCH_RESULT_SCREEN + "/${query}")
            })
        }

        composable(
            route = Router.SEARCH_RESULT_SCREEN_PARAM,
            arguments = listOf(
                navArgument("query") {
                    defaultValue = ""
                    nullable = false
                    type = NavType.StringType
                },
            )) { entry ->
            val parentEntry =
                remember(entry) { rootNavController.getBackStackEntry(Router.SEARCH_RESULT_SCREEN_PARAM) }
            val query = parentEntry.arguments?.getString("query") ?: " "
            SearchResultScreen(
                query,
                onGoToDetail = {
                               rootNavController.navigate(Router.PRODUCTS_GRAPH)
                },
                onBackNavigation = {
                    rootNavController.navigateUp()
                }
            )
        }
    }
}