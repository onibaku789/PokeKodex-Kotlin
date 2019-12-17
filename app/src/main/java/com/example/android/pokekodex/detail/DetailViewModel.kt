/*
 *  Copyright 2019, The Android Open Source Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.android.pokekodex.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.android.pokekodex.network.Pokemon
import com.example.android.pokekodex.R
import com.example.android.pokekodex.database.PokemonDatabaseDao
import com.example.android.pokekodex.database.PokemonEntity
import kotlinx.coroutines.*

/**
 *  The [ViewModel] associated with the [DetailFragment], containing information about the selected
 *  [Pokemon].
 */
class DetailViewModel(pokemon: Pokemon,
                      val database: PokemonDatabaseDao,
                      val app: Application) : ViewModel() {


    private val _selectedPokemon = MutableLiveData<Pokemon>()
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val selectedPokemon: LiveData<Pokemon>
        get() = _selectedPokemon




    init {
        _selectedPokemon.value = pokemon
    }




    val displayName = Transformations.map(selectedPokemon) {
        app.applicationContext.getString(R.string.pokemon_name, selectedPokemon.value?.name)
    }

    val displayBaseExp = Transformations.map(selectedPokemon) {
        app.applicationContext.getString(R.string.pokemon_base_exp, selectedPokemon.value?.base_experience)
    }



    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }



    fun onSave() {
        coroutineScope.launch {

            val newPokemon = PokemonEntity()
            println("SELECTEDPOKEMON:${selectedPokemon.value}")
            newPokemon.pokemonName = selectedPokemon.value?.name.toString()
            newPokemon.pokemonBaseExp = selectedPokemon.value?.base_experience!!

            println("NEWPOKEMON:$newPokemon")
            insert(newPokemon)

            println("AllPokmeons: ${getAllPokemons().isEmpty()}")
           // _showSnackbarEvent.value = true
        }
    }


    private suspend fun insert(pokemonEntity: PokemonEntity) {
        withContext(Dispatchers.IO) {
            database.insert(pokemonEntity)
        }
    }
    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }
    private suspend fun select(pokemonEntity: PokemonEntity):PokemonEntity? {
        return withContext(Dispatchers.IO) {
            var pokemonEntity = database.get(pokemonEntity.pokemonId)

            pokemonEntity
        }

    }

    private suspend fun getAllPokemons():List<PokemonEntity>{
        return withContext(Dispatchers.IO){
            var pokemonList = database.getAllPokemons()
            pokemonList
        }

    }

}

