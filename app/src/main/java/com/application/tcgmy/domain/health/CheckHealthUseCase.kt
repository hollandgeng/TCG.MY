package com.application.tcgmy.domain.health

import com.application.tcgmy.constants.Constants
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response

class CheckHealthUseCase {
    private val client = OkHttpClient()

    private val request = Request.Builder()
        .url("${Constants.SERVER_URL}/health")
        .build()

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun execute(): Response {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                response
            } catch (e: Exception) {
                e.printStackTrace()
                val mockRequest = Request.Builder()
                    .url("https://some-url.com")
                    .build()
                Response.Builder()
                    .request(mockRequest)
                    .protocol(Protocol.HTTP_1_1)
                    .code(999)
                    .message("Network error")
                    .build()
            }
        }
    }

}