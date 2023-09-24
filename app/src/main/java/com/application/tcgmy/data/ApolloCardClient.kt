package com.application.tcgmy.data

import com.apollographql.apollo3.ApolloClient
import com.application.tcgmy.CardsQuery
import com.application.tcgmy.GamesQuery
import com.application.tcgmy.domain.CardClient
import com.application.tcgmy.domain.SimpleCard
import com.application.tcgmy.type.GameCode

class ApolloCardClient(
    private val apolloClient: ApolloClient
): CardClient {

    override suspend fun getGames(): List<Game> {
        return apolloClient
            .query(GamesQuery())
            .execute()
            .data
            ?.games
            ?.map { it.toGame() }
            ?: emptyList()
    }

    override suspend fun getCards(query: String, games: GameCode): List<SimpleCard> {
        return apolloClient
            .query(
                CardsQuery(query = query, game = games)
            )
            .execute()
            .data
            ?.cards
            ?.map { it.toSimpleCard() }
            ?: emptyList()
    }
}