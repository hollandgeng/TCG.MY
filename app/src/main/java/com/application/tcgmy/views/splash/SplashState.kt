package com.application.tcgmy.views.splash

data class SplashState(
    val isLoading: Boolean = true,
    val isHealthy: Boolean = false,
    val errorCode: String = "",
    val errorMsg: String = ""
)
