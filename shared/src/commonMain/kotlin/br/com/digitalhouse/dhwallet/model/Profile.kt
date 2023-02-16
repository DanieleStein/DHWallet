package br.com.digitalhouse.dhwallet.model

import kotlinx.serialization.Serializable

@Serializable
class ProfileToken(val token: String, val user: Profile)

@Serializable
class Profile(val name: String, val email: String, val photo: String)
