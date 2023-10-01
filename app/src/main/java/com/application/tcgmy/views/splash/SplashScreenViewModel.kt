package com.application.tcgmy.views.splash

import androidx.lifecycle.ViewModel
import com.application.tcgmy.domain.health.CheckHealthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.Response
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private var checkHealthUseCase: CheckHealthUseCase
): ViewModel() {
    private val _splashState = MutableStateFlow(SplashState())
    val splashState = _splashState.asStateFlow()
    suspend fun doHealthCheck() {
        val response = checkHealthUseCase.execute()

        _splashState.update {
            it.copy(
                isLoading = true
            )
        }

        delay(3000)
        if (response.isSuccessful) {
            println("Server is healthy")
        } else {
            println("Server is not healthy, code: ${response.code}")
        }

        _splashState.update {
            it.copy(
                isLoading = false,
                isHealthy = response.isSuccessful,
                errorCode = response.code.toString(),
                errorMsg = response.message
            )
        }
    }
}