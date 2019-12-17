package com.example.android.pokekodex.network

import com.squareup.moshi.Json

data class PokemonList(

      val  count: Int,
      val next: String?,
      val previous: String?,
      @Json(name = "results")
      val results: List<PokemonElement>

)

data class PokemonElement (
        val name:String,
        @Json(name = "url")
        val url:String

)
