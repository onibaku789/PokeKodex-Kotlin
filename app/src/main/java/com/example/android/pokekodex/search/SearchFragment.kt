package com.example.android.pokekodex.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.android.pokekodex.databinding.FragmentSearchBinding

class SearchFragment :Fragment(){

    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val binding = FragmentSearchBinding.inflate(inflater)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this



        return  binding.root
    }
}