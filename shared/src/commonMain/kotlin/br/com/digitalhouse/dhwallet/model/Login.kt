package br.com.digitalhouse.dhwallet.model

import kotlinx.serialization.Serializable

@Serializable
data class Login(val email: String, val password: String) {
    fun validador(): Boolean {
        if (email == "usuario@kmm.com" && password == "12345") {
           return true
        } else {
           return false
        } //Podemos fazer dessa forma -> fun validador() = (usuario == "usuario@kmm.com" && senha == "12345")
    }     //Minha função é uma variavel, e ela retorna como boolean
}
