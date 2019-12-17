package com.example.android.pokekodex.database

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.pokekodex.network.Pokemon
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.isSupertypeOf
import kotlin.reflect.full.memberProperties


@Entity(tableName = "pokemon_table")
data class PokemonEntity(
        @PrimaryKey(autoGenerate = true)
        var pokemonId: Long = 0L,

        @ColumnInfo(name="pokemon_name")
        var pokemonName:String = "",

        @ColumnInfo(name="base_exp")
        var pokemonBaseExp: Int = -1

){

}