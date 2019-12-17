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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.pokekodex.R
import com.example.android.pokekodex.database.PokemonDatabase
import com.example.android.pokekodex.databinding.FragmentDetailBinding
import com.google.android.material.snackbar.Snackbar


class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val pokemon = DetailFragmentArgs.fromBundle(arguments!!).selectedPokemon
        val dataSource  = PokemonDatabase.getInstance(application).pokemonDatabaseDao
        val viewModelFactory = DetailViewModelFactory(pokemon,dataSource, application)

      val viewModel = ViewModelProviders.of(
                this, viewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel = viewModel


        viewModel.showSnackBarEvent.observe(this, Observer {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                        activity!!.findViewById(R.id.content),
                        getString(R.string.save_string),
                        Snackbar.LENGTH_SHORT
                ).show()

                viewModel.doneShowingSnackbar()
            }
        })

        return binding.root
    }
}