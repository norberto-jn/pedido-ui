package br.com.fourvrstudios.convidados.pedido.network.dto.response

import com.squareup.moshi.Json

data class FoodResponseDTO(

    @Json(name = "code")
    val code: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "phone")
    val phone: String,

    @Json(name = "food")
    val food: String,
)
