package br.com.digitalhouse.dhwallet.model

import kotlinx.serialization.Serializable

@Serializable
class RickMortyResult(val results: List<Personagem>) //Lista dos pensonagens(name e image da class Pensonagem)

@Serializable
class Personagem(val name: String, val image: String) //Vamos pegar o name e image dos personagens da api do RickMorty
