package br.com.fourvrstudios.convidados.pedido.network

import br.com.fourvrstudios.convidados.pedido.network.dto.request.FoodRequestDTO
import br.com.fourvrstudios.convidados.pedido.network.dto.response.FoodResponseDTO
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("customer/food/{code}")
    suspend fun finOne(@Path("code") code:Int):Response<FoodResponseDTO>

    @GET("customer/food")
    suspend fun finAll():Response<List<FoodResponseDTO>>

    @POST("customer/food")
    suspend fun save(@Body() dto: FoodRequestDTO):Response<FoodResponseDTO>

    @DELETE("customer/food/{code}")
    suspend fun delete(@Path("code") code:Int)
}