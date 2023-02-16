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

      //runBlocking (informando ao coroutines que vamos rodar nosso codigo na mainThread
        runBlocking { //função do coroutines que roda todo o nosso código sincronamente dentro da main thread
            val start = Clock.System.now().toEpochMilliseconds() //vai pegar o milisegundos
            //println(login1())//rodando sincronamente
            //println(perfil())//rodando sincronamente

            //Agora rodar de forma assincrona, os 2 em threads separadas.
            val loginAsync = async { login1() } //vai rodar estes dois de forma assincrona, os dois separados mas ao mesmo tempo
            val perfilAsync = async { perfil() }

            println(loginAsync.await())
            println(perfilAsync.await()) //aqui vai juntar os dois e trazer o resultado dos dois

            dados = "Tempo: ${(Clock.System.now().toEpochMilliseconds() - start)/1000} segundos"
        }

        return dados
    }

  fun loadTransaction(): List<Transaction> {
    val mockData =
      "[{\"logo\":\"https://media.licdn.com/dms/image/C4E03AQGzYGYIlmUzbg/profile-displayphoto-shrink_800_800/0/1640531489913?e=2147483647&v=beta&t=qmWLp-OvACiTmOfMIYk-T3bCq1R-KQkB7jXM9UsGvfI\",\"title\":\"Uber\",\"transactionType\":\"DEBIT\",\"value\":45.0,\"date\":\"2023-01-26\"},{\"logo\":\"https://media.licdn.com/dms/image/C4E03AQGzYGYIlmUzbg/profile-displayphoto-shrink_800_800/0/1640531489913?e=2147483647&v=beta&t=qmWLp-OvACiTmOfMIYk-T3bCq1R-KQkB7jXM9UsGvfI\",\"title\":\"Uber\",\"transactionType\":\"DEBIT\",\"value\":45.0,\"date\":\"2023-01-26\"},{\"logo\":\"https://media.licdn.com/dms/image/C4E03AQGzYGYIlmUzbg/profile-displayphoto-shrink_800_800/0/1640531489913?e=2147483647&v=beta&t=qmWLp-OvACiTmOfMIYk-T3bCq1R-KQkB7jXM9UsGvfI\",\"title\":\"Uber\",\"transactionType\":\"DEBIT\",\"value\":45.0,\"date\":\"2023-01-26\"},{\"logo\":\"https://media.licdn.com/dms/image/C4E03AQGzYGYIlmUzbg/profile-displayphoto-shrink_800_800/0/1640531489913?e=2147483647&v=beta&t=qmWLp-OvACiTmOfMIYk-T3bCq1R-KQkB7jXM9UsGvfI\",\"title\":\"Uber\",\"transactionType\":\"DEBIT\",\"value\":45.0,\"date\":\"2023-01-26\"},{\"logo\":\"https://media.licdn.com/dms/image/C4E03AQGzYGYIlmUzbg/profile-displayphoto-shrink_800_800/0/1640531489913?e=2147483647&v=beta&t=qmWLp-OvACiTmOfMIYk-T3bCq1R-KQkB7jXM9UsGvfI\",\"title\":\"Uber\",\"transactionType\":\"DEBIT\",\"value\":45.0,\"date\":\"2023-01-26\"}]"

    return Json.decodeFromString(mockData)
  }
}
