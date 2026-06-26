package com.dhiroj.dummyapp.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dhiroj.dummyapp.data.model.QuoteResponse
import com.dhiroj.dummyapp.presentation.QuoteCard
import com.dhiroj.dummyapp.presentation.viewModel.AuthViewModel
import com.dhiroj.dummyapp.presentation.viewModel.QuoteViewModel
import com.dhiroj.dummyapp.utils.NetworkResult
import org.koin.compose.viewmodel.koinViewModel

/*
@Composable
fun QuoteScreen(
    authViewModel: AuthViewModel = koinViewModel(),
    quoteViewModel: QuoteViewModel = koinViewModel()
) {

    val authState by authViewModel.uiState.collectAsState()
    val quoteState by quoteViewModel.getQuoteResponseState.collectAsState()

    LaunchedEffect(Unit) {
        authViewModel.getCurrentUser()
        quoteViewModel.getQuotes()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        authState.user?.let { user ->
            item {
                Text(
                    text = "Welcome ${user.username}",
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        when (val response = quoteState) {
            is NetworkResult.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is NetworkResult.Success -> {
                val quotesResponse = response.data.quotes
                val quoteslimt = response.data.limit
                val quoteskip = response.data.skip
                val quotestotal = response.data.total
                items(quotesResponse) { quote ->
                    QuoteCard(quote)
                }
            }

            is NetworkResult.Error -> {
                val error = (quoteState as NetworkResult.Error).error
                item {
                    Text(
                        text = "Error: ${error.message}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            is NetworkResult.Empty -> {
                item {
                    Text(
                        text = "No quotes found",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}*/
@Composable
fun QuoteScreen(
    authViewModel: AuthViewModel = koinViewModel(),
    quoteViewModel: QuoteViewModel = koinViewModel()
) {

    val authState by authViewModel.uiState.collectAsState()
    val quoteState by quoteViewModel.getQuoteResponseState.collectAsState()

    LaunchedEffect(Unit) {
        authViewModel.getCurrentUser()
        quoteViewModel.getQuotes()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        when (val state = quoteState) {

            is NetworkResult.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is NetworkResult.Error -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.error.message ?: "Something went wrong",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            is NetworkResult.Success -> {

                val response = state.data

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item {

                        WelcomeHeader(
                            username = authState.user?.username ?: "Guest"
                        )
                    }

                    item {

                        StatsCard(
                            total = response.total,
                            limit = response.limit,
                            skip = response.skip
                        )
                    }

                    items(response.quotes) { quote ->

                        QuoteCard(
                            quote = quote
                        )
                    }
                }
            }

            NetworkResult.Empty -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No Quotes Available")
                }
            }
        }
    }
}
@Composable
fun WelcomeHeader(
    username: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = "Daily Quotes",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Welcome back, $username",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Discover inspiration one quote at a time.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
@Composable
fun StatsCard(
    total: Int,
    limit: Int,
    skip: Int
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            StatsItem("Total", total.toString())

            StatsItem("Showing", limit.toString())

            StatsItem("Skip", skip.toString())
        }
    }
}
@Composable
fun StatsItem(
    title: String,
    value: String
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}