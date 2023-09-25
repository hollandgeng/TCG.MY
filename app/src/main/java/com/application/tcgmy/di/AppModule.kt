package com.application.tcgmy.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.application.tcgmy.data.ApolloCardClient
import com.application.tcgmy.domain.CardClient
import com.application.tcgmy.domain.GetCardUseCase
import com.application.tcgmy.domain.GetGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        // Create a custom OkHttpClient with a read timeout
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS) // Set the read timeout to 10 seconds
            .build()

        return ApolloClient.Builder()
            .serverUrl("http://192.168.68.135:8080/query")
            .okHttpClient(okHttpClient)
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