package com.example.weatherspot.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SearchViewModel(

): ViewModel() {
    private val _query = MutableStateFlow("")

    fun setQuery(query: String) {
        _query.update { query }
    }

//    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
//    val searchResultsState: StateFlow<UiState<SearchResponseDomain>> = _query
//        .debounce(2000L)
//        .distinctUntilChanged()
//        .flatMapConcat { query ->
//
//        }
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = UiState.Loading()
//        )
}