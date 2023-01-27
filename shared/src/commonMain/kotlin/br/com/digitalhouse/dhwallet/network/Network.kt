package br.com.digitalhouse.dhwallet.network

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock

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
}