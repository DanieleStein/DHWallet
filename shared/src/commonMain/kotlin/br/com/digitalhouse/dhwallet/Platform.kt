package br.com.digitalhouse.dhwallet

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform