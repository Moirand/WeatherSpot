package com.example.weatherspot.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.component.MySearchResultCard
import com.example.common.component.MyTextInput
import com.example.domain.model.response.SearchResponseDomain
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
//    val searchResultState by remember { viewModel.searchResultsState }.collectAsState()

    var searchResults by remember { mutableStateOf(SearchResponseDomain(emptyList())) }

//    LaunchedEffect(searchResultState) {
//        when (searchResultState) {
//            is UiState.Error -> {}
//            is UiState.Loading -> {}
//            is UiState.Success -> {
//                searchResultState.data?.let {
//                    searchResults = it
//                }
//            }
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchField(
            query = searchQuery,
            onSearch = { query ->
                searchQuery = query
                viewModel.setQuery(query)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        SearchResult(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchField(
    modifier: Modifier = Modifier,
    query: String = "",
    onSearch: (String) -> Unit = {}
) {
    MyTextInput(
        value = query,
        onValueChange = { onSearch(it) },
        placeholder = "Search city by name, IP address, or lat & long",
        imeAction = ImeAction.Search,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchResult(
    modifier: Modifier = Modifier,
    searchResult: SearchResponseDomain = SearchResponseDomain(emptyList())
) {
    Column(
        modifier = modifier
    ) {
        LazyColumn {
            items(searchResult.searchResponseDomain) {
                MySearchResultCard(
                    city = it?.name ?: "",
                    region = it?.name ?: "",
                    country = it?.name ?: "",
                    lat = it?.name ?: "",
                )
            }
        }
    }
}