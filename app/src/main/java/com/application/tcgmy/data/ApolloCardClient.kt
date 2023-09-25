package com.application.tcgmy.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.application.tcgmy.CardsQuery
import com.application.tcgmy.GamesQuery
import com.application.tcgmy.domain.CardClient
import com.application.tcgmy.domain.SimpleCard
import com.application.tcgmy.type.GameCode

class ApolloCardClient(
    private val apolloClient: ApolloClient
): CardClient {

    override suspend fun getGames(): List<Game> {
        try {
            val response = apolloClient.query(GamesQuery()).execute()

            // Check for GraphQL errors
            if (response.hasErrors()) {
                for (error in response.errors!!) {
                    // Handle each GraphQL error
                    // You can log the error or perform any necessary actions
                    println("GraphQL Error: ${error.message}")
                }
            }

            return response.data?.games?.map { it.toGame() } ?: emptyList()
        } catch (e: ApolloException) {
            // Handle Apollo exceptions (network errors, GraphQL errors)
            e.printStackTrace()
            return emptyList()
        }
    }

    override suspend fun getCards(query: String, games: GameCode): List<SimpleCard> {
        try {
            val response = apolloClient.query(CardsQuery(query = query, game = games)).execute()

            // Check for GraphQL errors
            if (response.hasErrors()) {
                for (error in response.errors!!) {
                    // Handle each GraphQL error
                    // You can log the error or perform any necessary actions
                    println("GraphQL Error: ${error.message}")
                }
            }

            return response.data?.cards?.map { it.toSimpleCard() } ?: emptyList()
        } catch (e: ApolloException) {
            // Handle Apollo exceptions (network errors, GraphQL errors)
            e.printStackTrace()
            return emptyList()
        }
    }
}