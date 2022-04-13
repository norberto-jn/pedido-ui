package br.com.fourvrstudios.convidados.pedido.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi=Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

class RetrofitInstance {
    companion object { // Compantion object para que a instancia do retrofit esteja acessível na aplicação
        val retrofit = Retrofit.Builder()
            .baseUrl("https://18fd-2804-d45-bfc8-c800-e49f-f81-22a-1ff1.ngrok.io") // URL base
            .addConverterFactory(MoshiConverterFactory.create(moshi)) // Conversor de Json em objetos Kotlin // Podemos utilizar o .asLenient() para nullable
            .build() // Cria o Retrofit Object
            .create(ApiService::class.java) // Interface onde estarão configurados os métodos HTTP
    }
}