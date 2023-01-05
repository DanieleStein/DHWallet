package br.com.digitalhouse.dhwallet.model

data class Login(val usuario: String, val senha: String) {
    fun validador(): Boolean {
        if (usuario == "usuario@kmm.com" && senha == "12345") {
           return true
        } else {
           return false
        } //Podemos fazer dessa forma -> fun validador() = (usuario == "usuario@kmm.com" && senha == "12345")
    }     //Minha função é uma variavel, e ela retorna como boolean
}