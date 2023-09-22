package com.application.tcgmy.views.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.tcgmy.domain.GetCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCardUseCase: GetCardUseCase
): ViewModel() {

    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    fun searchCard(query: String) {
        viewModelScope.launch {
            _searchState.update { it.copy(
                isLoading = true
            ) }
            _searchState.update { it.copy(
                cards = getCardUseCase.execute(query = query),
                isLoading = false
            ) }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}