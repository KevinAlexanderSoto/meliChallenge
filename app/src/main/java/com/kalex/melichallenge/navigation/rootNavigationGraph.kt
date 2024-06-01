package com.kalex.melichallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kalex.melichallenge.navigation.graphs.productsNavigationGraph
import com.kalex.melichallenge.navigation.graphs.searchNavigationGraph

@Composable
fun RootNavigationGraph(
    rootNavController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = rootNavController,
        startDestination = startDestination
    ) {
        searchNavigationGraph(rootNavController)
        productsNavigationGraph(rootNavController)
    }
}
