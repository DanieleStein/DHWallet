package br.com.digitalhouse.dhwallet.model

import kotlinx.serialization.Serializable

@Serializable //Lista de RickMorty com os personagens
class RickMortyResult(val results: List<Personagem>)

@Serializable //Personagem, nome e Imagem
class Personagem(val name: String, val image: String)
