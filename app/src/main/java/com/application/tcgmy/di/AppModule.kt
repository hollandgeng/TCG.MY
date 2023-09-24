package com.application.tcgmy.di

import com.apollographql.apollo3.ApolloClient
import com.application.tcgmy.data.ApolloCardClient
import com.application.tcgmy.domain.CardClient
import com.application.tcgmy.domain.GetCardUseCase
import com.application.tcgmy.domain.GetGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("http://192.168.68.135:8080/query")
            .build()
    }

    @Provides
    @Singleton
    fun provideCardClient(apolloClient: ApolloClient): CardClient {
        return ApolloCardClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetCardUseCase(cardClient: CardClient): GetCardUseCase {
        return GetCardUseCase(cardClient)
    }

    @Provides
    @Singleton
    fun provideGetGamesUseCase(cardClient: CardClient): GetGamesUseCase {
        return GetGamesUseCase(cardClient)
    }
}