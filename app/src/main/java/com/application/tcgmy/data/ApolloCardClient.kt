package com.application.tcgmy.data

import com.apollographql.apollo3.ApolloClient
import com.application.tcgmy.CardsQuery
import com.application.tcgmy.domain.CardClient
import com.application.tcgmy.domain.SimpleCard

class ApolloCardClient(
    private val apolloClient: ApolloClient
): CardClient {
    override suspend fun getCards(query: String): List<SimpleCard> {
        return apolloClient
            .query(
                CardsQuery()
            )
            .execute()
            .data
            ?.cards
            ?.map { it.toSimpleCard() }
            ?: emptyList()
    }
}