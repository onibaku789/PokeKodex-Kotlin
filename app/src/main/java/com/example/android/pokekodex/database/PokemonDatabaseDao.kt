package com.example.android.pokekodex.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface PokemonDatabaseDao {

    @Insert
    fun insert(pokemon:PokemonEntity)

    @Update
    fun update(night: PokemonEntity)


    @Query("Select * from pokemon_table WHERE pokemonId = :key")
    fun get(key: Long):PokemonEntity?

    @Query("Delete from pokemon_table")
    fun clear()

    @Query("SELECT * FROM pokemon_table ORDER BY pokemonId DESC")
    fun getAllPokemons(): List<PokemonEntity>





}
