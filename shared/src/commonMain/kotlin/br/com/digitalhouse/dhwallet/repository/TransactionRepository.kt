package br.com.digitalhouse.dhwallet.repository

import br.com.digitalhouse.dhwallet.api.Api
import br.com.digitalhouse.dhwallet.extension.updateState
import br.com.digitalhouse.dhwallet.util.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TransactionRepository(
  private val api: Api = Api.instance) {//Instancia da nossa Api(consumo da api)
  private val dispatcher: CoroutineDispatcher = Dispatchers.Default

  suspend fun getTransactions() = flow { //suspende fun(Chamadas da nossa api)flow(executar a nossa api dentro de um flow)e o flow vai retornar a lista de transações
    val chamada = api.getAll().transactions //nossa chamada do tipo List transaction

    if(chamada.isEmpty()) { //se nossa chamada(lista transaction) for vazia
      emit(DataResult.Empty) //emite dataResult de Empty
    } else { //se não
      emit(DataResult.Sucess(chamada)) //emite DataResult de sucesso, mostrando emissao da chamada(lista de transactions)
    }
  }.updateState().flowOn(dispatcher)

  suspend fun getProfile() = flow{
    emit(DataResult.Sucess(api.profile())) //emissao do profile(não precisa verificar se é vazio, pois ou tem um perfil ou não
  }.updateState().flowOn(dispatcher)

  companion object {
    val instance by lazy { TransactionRepository() } //Fazendo a instancia da nossa classe de TransactionRepository
  }
 }
