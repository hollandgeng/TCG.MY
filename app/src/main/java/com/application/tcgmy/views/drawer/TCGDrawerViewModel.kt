package com.application.tcgmy.views.drawer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.tcgmy.data.Game
import com.application.tcgmy.domain.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TCGDrawerViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
): ViewModel() {
    private val _drawerState = MutableStateFlow(DrawerState())
    val drawerState = _drawerState.asStateFlow()

    fun getListOfGames() {
        viewModelScope.launch {
            _drawerState.update {  it.copy(
              games = sortGames(
                  games = getGamesUseCase.execute()
              )
            ) }
        }
    }

    private fun sortGames(games: List<Game>): List<Game> {
        return games.sortedBy { it.title }
    }
}