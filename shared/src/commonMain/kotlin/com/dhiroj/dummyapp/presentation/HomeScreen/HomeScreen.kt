package com.dhiroj.dummyapp.presentation.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dhiroj.dummyapp.Enums.HomeTab
import com.dhiroj.dummyapp.presentation.ProductScreen.ProductScreen
import com.dhiroj.dummyapp.presentation.screen.QuoteScreen

@Composable
fun HomeScreen() {

    var selectedTab by rememberSaveable {
        mutableStateOf(HomeTab.QUOTES)
    }
    Scaffold(
        topBar = {
            Column {

                Text(
                    text = "My App",
                    modifier = Modifier.padding(
                        start = 20.dp,
                        top = 20.dp,
                        bottom = 12.dp
                    ),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold
                )

                PrimaryTabRow(
                    selectedTabIndex = selectedTab.ordinal
                ) {

                    Tab(
                        selected = selectedTab == HomeTab.QUOTES,
                        onClick = {
                            selectedTab = HomeTab.QUOTES
                        },
                        text = {
                            Text("Quotes")
                        }
                    )

                    Tab(
                        selected = selectedTab == HomeTab.PRODUCTS,
                        onClick = {
                            selectedTab = HomeTab.PRODUCTS
                        },
                        text = {
                            Text("Products")
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            HomeTab.QUOTES -> {
                QuoteScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }

            HomeTab.PRODUCTS -> {
                ProductScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}