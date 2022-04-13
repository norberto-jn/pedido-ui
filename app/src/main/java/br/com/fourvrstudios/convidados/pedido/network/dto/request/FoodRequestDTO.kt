package br.com.fourvrstudios.convidados.pedido.network.dto.request

import com.squareup.moshi.Json

data class FoodRequestDTO(

    @Json(name = "name")
    val name: String,

    @Json(name = "phone")
    val phone: String,

    @Json(name = "food")
    val food: String,

)
