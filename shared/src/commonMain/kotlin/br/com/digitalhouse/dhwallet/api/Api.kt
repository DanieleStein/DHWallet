package br.com.digitalhouse.dhwallet.api

import br.com.digitalhouse.dhwallet.model.Login
import br.com.digitalhouse.dhwallet.model.Profile
import br.com.digitalhouse.dhwallet.model.ProfileToken
import br.com.digitalhouse.dhwallet.model.TransectionResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlin.native.concurrent.ThreadLocal

//essa classe vai ser responsavel pelo nosso cliente.
//1-(vai iniciar nosso cliente com o Ktor)
//2-(vai ter todas as chamadas do nosso projeto)

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

    defaultRequest { //COMENTAR
      contentType(ContentType.Application.Json)
      accept(ContentType.Application.Json)
      header("Authorization", "Bearer $token")
    }
  }

  //Nossas chamadas
  //A api que nós vamos usar é do RickMorty, e dela eu quero uma lista com nome e image de todos os personagens
  //função do nosso coroutines, que vai fazer as jogadas das threads
  suspend fun getAll(): TransectionResponse { //suspend fun(funcao do coroutines, e nela ele vai ficar jogando as funções, controlando startando e parando)getAll(todos os dados)//Aqui vai nos retornar a lista de dados da nossa api
     return httpClient.get("$DEFAULT_URL/transaction").body()//url da nossa api//no retorno da api, vai fazer o get onde queremos o body dela
  }

  suspend fun login(login: Login): ProfileToken { //Herdando do model Profile(name e token)
    return httpClient.post("$DEFAULT_URL/login") {//Pegar o login da API(post)
     setBody(login)//setBody(enviando)
    }.body()//body(retornando)
  }

  suspend fun profile(): Profile = httpClient.get("$DEFAULT_URL/user/profile").body() //COMENTAR

  @ThreadLocal //COMENTAR
  companion object { //Tudo dentro do meu companion ele inicia a partir do momento que eu instanciei a minha classe(Funciona como Singleton-Garante que uma classe tenha apenas uma instância, ponto de acesso global da instacia)
    val instance by lazy { Api() } //Quero iniciar a minha api, somente quando eu instantiar a api e chamar o instance//Instanciando a nossa api, toda a vez que precisar chamar ela uso o instance
    var token = ""
    const val DEFAULT_URL = "https://dh-wallet-2.herokuapp.com"
  }
}
