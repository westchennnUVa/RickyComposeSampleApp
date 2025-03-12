package com.example.rickysampleapp.network.ktor

import com.example.rickysampleapp.network.RickyNetworkDataSource
import com.example.rickysampleapp.network.dto.CharacterDetailDto
import com.example.rickysampleapp.network.dto.CharactersDto
import com.example.rickysampleapp.network.utils.NetworkResponse
import com.example.rickysampleapp.network.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RickyKtorClient @Inject constructor(
    networkJson: Json
): RickyNetworkDataSource {

    private val ktorClient = HttpClient(OkHttp) {
        defaultRequest { url(BASE_URL) }

        install(Logging) {
            logger = Logger.SIMPLE
        }

        install(ContentNegotiation) {
            json(networkJson)
        }

        // TODO
        // expectSuccess = true
    }

    override suspend fun getCharacter(id: Int): NetworkResponse<CharacterDetailDto> = safeApiCall {
        ktorClient
            .get("character/$id")
            .body<CharacterDetailDto>()
    }

    override suspend fun getCharacters(page: Int): NetworkResponse<CharactersDto> = safeApiCall {
        ktorClient
            .get {
                url {
                    appendPathSegments("character")
                    parameters.append("page", page.toString())
                }
            }
            .body<CharactersDto>()
    }

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}