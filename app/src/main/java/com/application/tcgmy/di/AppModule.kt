package com.application.tcgmy.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.application.tcgmy.constants.Constants
import com.application.tcgmy.data.ApolloCardClient
import com.application.tcgmy.domain.CardClient
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
            .serverUrl("${Constants.SERVER_URL}/query")
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideCardClient(apolloClient: ApolloClient): CardClient {
        return ApolloCardClient(apolloClient)
    }
}