

package com.example.android.pokekodex.overview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.pokekodex.network.Pokemon
import com.example.android.pokekodex.network.PokemonApi
import com.example.android.pokekodex.network.PokemonList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class OverviewViewModel : ViewModel() {


    private val _navigateToSelectedProperty = MutableLiveData<Pokemon>()
    val navigateToSelectedProperty: LiveData<Pokemon>
        get() = _navigateToSelectedProperty


    private var viewModelJob = Job()


    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _pokemonList = MutableLiveData<List<Pokemon>>()

    val pokemonList: LiveData<List<Pokemon>>
        get() = _pokemonList

    init {

        getPokemons()
    }


    /* private fun getPokemons(pokemonName:String = "ditto"){
         coroutineScope.launch {
             var getPokemonsDeferred = PokemonApi.retrofitService.getPokemon(pokemonName)
             try {
                 val pokemonList = ArrayList<Pokemon>()
                 val pokemon :Pokemon
                  pokemon = getPokemonsDeferred.await()
                 pokemonList.add(pokemon)
                 _pokemonList.value = pokemonList
             }catch (e: Exception){
                 _pokemonList.value = ArrayList()
             }
         }
     }*/
    private fun getPokemons() {
        coroutineScope.launch {
            var getPokemonListDeferred = PokemonApi.retrofitService.getPokemonList()
            val resultPokemonList = ArrayList<Pokemon>()
            var pokemonList: PokemonList? = null
            try {
                pokemonList = getPokemonListDeferred.await()
            } catch (e: Exception) {
                _pokemonList.value = listOf()
            }

            for (i in 0..100) {
               // var getPokemonDeferred = PokemonApi.retrofitService.getPokemon(pokemonList?.results?.get(i)?.name)
                var getPokemonDeferred = PokemonApi.retrofitService.getPokemonByUrl(pokemonList?.results?.get(i)?.url)
                try {
                    resultPokemonList.add(getPokemonDeferred.await())
                } catch (e: Exception) {

                }
            }
            _pokemonList.value = resultPokemonList
        }
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



    fun displayPropertyDetails(pokemon: Pokemon) {
        _navigateToSelectedProperty.value = pokemon
    }


    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}
