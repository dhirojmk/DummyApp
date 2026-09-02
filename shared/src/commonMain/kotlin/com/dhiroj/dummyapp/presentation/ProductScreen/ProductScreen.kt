package com.dhiroj.dummyapp.presentation.ProductScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dhiroj.dummyapp.data.model.product.ProductsResponse
import com.dhiroj.dummyapp.presentation.ProductScreen.Componets.ProductGrid
import com.dhiroj.dummyapp.presentation.viewModel.ViewModel
import com.dhiroj.dummyapp.utils.NetworkResult
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier, viewModel: ViewModel = koinViewModel()
) {
    val productsState by viewModel.getProductsResponseState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }

    Scaffold(
        modifier = modifier, containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier.padding(12.dp)
            ) {

                Text(
                    text = "Discover",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Find something you'll love today ✨",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                val products = when {
                    productsState is NetworkResult.Success -> {
                        (productsState as NetworkResult.Success<ProductsResponse>).data.products
                    }

                    else -> emptyList()
                }
                ProductGrid(
                    products = products,
                    onProductClick = { product ->
                        // Navigate using product.id
                        // navController.navigate("productDetail/${product.id}")
                    }
                )
            }
        }
    }
}


