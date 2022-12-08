package com.oscarSainz.pokedex.ui.pokelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oscarSainz.pokedex.model.api.PokeApiResponse
import com.oscarSainz.pokedex.model.api.PokeResult
import com.oscarSainz.pokedex.model.api.service.PokeApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeListViewModel : ViewModel() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val pokemonList = MutableLiveData<List<PokeResult>>()

    fun getPokemonList(){

        CoroutineScope(Dispatchers.IO).launch {

            val service: PokeApiService = retrofit.create(PokeApiService::class.java)

            val call = service.getPokemonList(900,0)

            call.enqueue(object : Callback<PokeApiResponse>{
                override fun onResponse(call: Call<PokeApiResponse>, response: Response<PokeApiResponse>) {
                    response.body()?.results?.let { list ->
                        pokemonList.postValue(list)
                    }
                }
                override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                    call.cancel()
                }
            })
        }
    }
}