package com.example.weathercheck2000.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weathercheck2000.ui.cityDetail.CityDetailScreen
import com.example.weathercheck2000.ui.cityDetail.CityDetailViewModel
import com.example.weathercheck2000.ui.home.HomeScreen
import com.example.weathercheck2000.ui.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

enum class Screen {
    HOME,
    CITY,
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object CityDetail : NavigationItem(Screen.CITY.name)
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        // -------- HOME -----------------
        composable(NavigationItem.Home.route) {


            val viewModel: HomeViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsState()

            HomeScreen(
                onCityClicked = { NavigationItem.CityDetail.route+"/"+it.toString() },
                uiState = uiState
            )
        }

        // -------- CITY DETAIL -----------------
        composable(NavigationItem.CityDetail.route+"/{cityId}") { backStackEntry ->
            val cityId = backStackEntry.arguments?.getString("cityId")

            val viewModel: CityDetailViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsState()

            CityDetailScreen(
                uiState = uiState,
                cityId = cityId ?: "",
            )
        }
    }
}