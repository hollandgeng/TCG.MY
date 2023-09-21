package com.application.tcgmy.domain

interface CardClient {
    suspend fun getCards(query: String): List<SimpleCard>
}