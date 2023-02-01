package br.com.digitalhouse.dhwallet.network

import br.com.digitalhouse.dhwallet.model.Transaction
import br.com.digitalhouse.dhwallet.model.TransactionType
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Network {

    suspend fun login1(): Int {
        delay(1000)
        return 0

    }

    suspend fun perfil(): Int {
        delay(1000)
        return 1
    }

    fun carregarDados(): String {
        var dados = ""

        runBlocking { //função do coroutines que roda todo o nosso código sincronamente dentro da main thread
            val start = Clock.System.now().toEpochMilliseconds() //vai pegar o milisegundos
            //println(login1())//rodando sincronamente
            //println(perfil())//rodando sincronamente

            //Agora rodar de forma assincrona, os 2 em threads separadas.
            val loginAsync = async { login1() } //vai rodar estes dois de forma assincrona, os dois separdos mas ao mesmo tempo
            val perfilAsync = async { perfil() }

            println(loginAsync.await())
            println(perfilAsync.await()) //await vai juntar os dois e trazer o resultado

            dados = "Tempo: ${(Clock.System.now().toEpochMilliseconds() - start)/1000} segundos"
        }

        return dados
    }

  fun loadTransaction(): List<Transaction> {//Vamos retornar uma lista de transações
    val mockList = MutableList(5) {//Aqui vamos fazer a instancia da nossa transação
      Transaction(
        "https://www.freepnglogos.com/uploads/uber-logo-png-0.png",
        "Uber",
        TransactionType.DEBIT,
        45.00,
        "2023-01-29" //Colocando a nossa data da transação
      )
    }
    val serializer = Json.encodeToString(mockList)
    println("Serializando (Formato JSON)")
    println(serializer)

    print("Deserializando (Formato OBJ)")
    val deserialize = Json.decodeFromString<List<Transaction>>(serializer)//Ele pede o formato que é <List<Transaction>>
    println(deserialize)

    return deserialize
  }
}
