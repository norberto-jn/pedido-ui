package br.com.fourvrstudios.convidados.pedido.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fourvrstudios.convidados.pedido.network.RetrofitInstance
import br.com.fourvrstudios.convidados.pedido.network.dto.request.FoodRequestDTO
import br.com.fourvrstudios.convidados.pedido.network.dto.response.FoodResponseDTO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class RetrofitViewModel : ViewModel() {

    //private val  _status=MutableLiveData<Rest>

    private val _response = MutableLiveData<FoodResponseDTO>()
    val response: LiveData<FoodResponseDTO?>
    get() = _response

    private val _responseList=MutableLiveData<List<FoodResponseDTO>?>()
    val responseList:LiveData<List<FoodResponseDTO>?>
    get() = _responseList

    init {
        //save()
        //getOne()
        //getRestApiResponse()
        //getAllPhotos()
        //getPhotosByAlbum()
        //postPhoto()
        //replacePhoto()
        //updatePhoto()
        //deletePhoto()
        // getRestApiResponse()
        //postField()
    }

    fun findOne(code:Int):Job=viewModelScope.launch {
        try {
            _response.value=RetrofitInstance.retrofit.finOne(code).body()

        }catch (e:Exception){
            Log.i("ERRO","$e")
        }
    }

    fun findAll():Job=viewModelScope.launch {
        try {
            _responseList.value=RetrofitInstance.retrofit.finAll().body()

        }catch (e:Exception){
            Log.i("ERRO","$e")
        }
    }

   fun save(dto: FoodRequestDTO):Job=viewModelScope.launch {
        try {
            _response.value=RetrofitInstance.retrofit.save(dto).body()

        }catch (e:Exception){
            Log.i("ERRO","$e")
        }
   }
    fun delete(code:Int):Job=viewModelScope.launch {
        try {
            RetrofitInstance.retrofit.delete(code)
        }catch (e:Exception){
            Log.i("ERRO","$e")
        }
    }

    override fun onCleared() {
        super.onCleared()
        //getData().cancel() - As corrotinas em viewModelScope já são canceladas automaticamente.
    }
}