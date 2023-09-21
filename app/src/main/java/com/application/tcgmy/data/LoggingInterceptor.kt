package com.application.tcgmy.data

import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LoggingInterceptor : HttpInterceptor {
    override suspend fun intercept(request: HttpRequest, chain: HttpInterceptorChain): HttpResponse {
        val requestBody = Json.encodeToString(request.body)
        println("Request JSON: $requestBody")

        val response = chain.proceed(request)

        val responseBody = Json.encodeToString(response.body)
        println("Response JSON: $responseBody")

        return response
    }
}