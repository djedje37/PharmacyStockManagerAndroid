package com.djedje37.pharmacystockmanager.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.djedje37.pharmacystockmanager.presentation.ui.detail.ProductDetailRoute
import com.djedje37.pharmacystockmanager.presentation.ui.product.ProductRoute

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = ProductList) {
        composable<ProductList> {
            ProductRoute(onProductClick = { id -> navController.navigate(ProductDetail(id)) })
        }
        composable<ProductDetail> {
            ProductDetailRoute(onBack = { navController.popBackStack() })
        }
    }
}