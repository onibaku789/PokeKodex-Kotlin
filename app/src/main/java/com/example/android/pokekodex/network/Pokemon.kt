/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.pokekodex.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
        @Json(name = "id")
        val id: Long,
        @Json(name = "name")
        val name: String,
        @Json(name="base_experience")
        val base_experience:Int,
        @Json(name = "sprites")
        val sprites:PokemonSprites
) : Parcelable

@Parcelize
data class PokemonSprites(
        @Json(name = "back_default")
        val backDefault: String?,
        @Json(name = "back_shiny")
        val backShiny: String?,
        @Json(name = "front_default")
        val frontDefault: String?,
        @Json(name = "front_shiny")
        val frontShiny: String?,
        @Json(name = "back_female")
        val backFemale: String?,
        @Json(name = "back_shiny_female")
        val backShinyFemale: String?,
        @Json(name = "front_female")
        val frontFemale: String?,
        @Json(name = "front_shiny_female")
        val frontShinyFemale: String?

) : Parcelable
