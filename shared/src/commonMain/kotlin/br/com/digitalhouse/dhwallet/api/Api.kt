package br.com.digitalhouse.dhwallet.api

import br.com.digitalhouse.dhwallet.model.Login
import br.com.digitalhouse.dhwallet.model.RickMortyResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

//essa classe vai ser responsavel pelo nosso cliente.
//1-(vai iniciar nosso cliente com o Ktor)
//2-(vai ter todas as chamadas que vamos ter no nosso projeto)

class Api {
  private val httpClient = HttpClient {//fazendo a chamada do httpClient
    install(ContentNegotiation) {//a forma que vai fazer o contentNegotiation é com Json
      json(
        Json {
          ignoreUnknownKeys = true //se a api tiver mais dados do que eu tenho no meu modelo, ele vai ignorar
          useAlternativeNames = false //fiel ao modelo
        }
      )
    }
  }

  //Nossas chamadas
  //A api que nós vamos usar é do RickMorty, e dela eu quero uma lista com nome e image de todos os personagens
  //função do nosso coroutines, que vai fazer as jogadas das threads
  suspend fun getAll(): RickMortyResult{
    return httpClient.get("https://rickandmortyapi.com/api/character").body() //no retorno da apy, queremos o body dela
  }

  //suspend fun login(login: Login): Profile {
  //  httpClient.post("https://dh-food-api.herokuapp.com/login") {
  //    setBody(login)
  //  }.body()
  //}

  companion object { //Tudo dentro do meu companion ele inicia a partir do momento que eu instanciei a minha classe
    val instance by lazy { Api() } //Quero iniciar a minha api, somente quando eu instantiar a api e chamar o instance
  }
}
