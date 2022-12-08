package com.oscarSainz.pokedex.ui.pokeinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oscarSainz.pokedex.model.api.Pokemon
import com.oscarSainz.pokedex.model.api.service.PokeApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeInfoViewModel : ViewModel() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val pokemonInfo = MutableLiveData<Pokemon>()

    fun getPokemonInfo(id: Int){

        CoroutineScope(Dispatchers.IO).launch {

            val service: PokeApiService = retrofit.create(PokeApiService::class.java)

            val call = service.getPokemonInfo(id)

            call.enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    response.body()?.let { pokemon ->
                        pokemonInfo.postValue(pokemon)
                    }
                }
                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    call.cancel()
                }
            })
        }
    }
}