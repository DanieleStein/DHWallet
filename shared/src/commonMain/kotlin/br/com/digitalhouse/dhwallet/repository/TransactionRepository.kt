package br.com.digitalhouse.dhwallet.repository

import br.com.digitalhouse.dhwallet.api.Api
import kotlinx.coroutines.flow.flow

class TransactionRepository(
  private val api: Api = Api.instance) {//Instancia da nossa Api(consumo da api)

  suspend fun getTransactions() = flow { //suspende fun(Chamadas da nossa api)flow(executar a nossa api dentro de um flow)e o flow vai retornar a lista de transações
    val chamada = api.getAll().transactions //nossa chamada do tipo List transaction
    emit(chamada) //emissao da lista
  }

  suspend fun getProfile() = flow{
    emit(api.profile()) //emissao do profile
  }

  companion object {
    val instance by lazy { TransactionRepository() } //Fazendo a instancia da nossa classe de TransactionRepository
  }
 }
