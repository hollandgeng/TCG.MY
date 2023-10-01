package com.application.tcgmy.di

import com.application.tcgmy.domain.CardClient
import com.application.tcgmy.domain.GetCardUseCase
import com.application.tcgmy.domain.GetGamesUseCase
import com.application.tcgmy.domain.health.CheckHealthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetCardUseCase(cardClient: CardClient): GetCardUseCase {
        return GetCardUseCase(cardClient)
    }

    @Provides
    @ViewModelScoped
    fun provideGetGamesUseCase(cardClient: CardClient): GetGamesUseCase {
        return GetGamesUseCase(cardClient)
    }

    @Provides
    @ViewModelScoped
    fun provideCheckHealthUseCase(): CheckHealthUseCase {
        return CheckHealthUseCase()
    }
}