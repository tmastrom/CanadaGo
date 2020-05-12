package com.tmastro.canadago.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tmastro.canadago.R
import com.tmastro.canadago.database.AnimalDatabase
import com.tmastro.canadago.databinding.GameFragmentBinding

class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views
        val binding : GameFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = AnimalDatabase.getInstance(application).databaseDao()
        val viewModelFactory = GameViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)
        binding.vm = viewModel

        val adapter = ItemListAdapter()
        binding.itemList.adapter = adapter
        viewModel.items.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}