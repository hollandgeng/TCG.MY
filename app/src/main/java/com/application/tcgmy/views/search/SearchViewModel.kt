package com.application.tcgmy.views.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.tcgmy.constants.GameTitle
import com.application.tcgmy.domain.GetCardUseCase
import com.application.tcgmy.domain.SimpleCard
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
            // TODO: Update dynamic game selection via drawer
            _searchState.update { it.copy(
//                cards = getCardUseCase.execute(query = query),
                sortedCards = sortCards(cards = getCardUseCase.execute(query = query, game = GameTitle.YGO)),
                isLoading = false
            ) }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private fun sortCards(cards: List<SimpleCard>): Map<String, List<SimpleCard>> {

        val result: Map<String, List<SimpleCard>> = cards.groupBy {
            it.rarity
        }

        // Sort Rarity
        val sortedRarity = result.keys.sortedByDescending { key ->
            result[key]?.maxByOrNull { item ->
                item.score
            }?.score
        }

        var sortedResult = sortedRarity.associateWith { key ->
            result[key] ?: emptyList()
        }

        // Sort List of cards
        sortedResult = sortedResult.mapValues { entry ->
            entry.value.sortedByDescending { item ->
                item.score
            }
        }

        return sortedResult
    }
}