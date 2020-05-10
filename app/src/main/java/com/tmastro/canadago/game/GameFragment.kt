package com.tmastro.canadago.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
        // Add listener to the button
        binding.resetButton.setOnClickListener { onReset() }

        val application = requireNotNull(this.activity).application
        val dataSource = AnimalDatabase.getInstance(application).databaseDao
        val viewModelFactory = GameViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)
        binding.vm = viewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }

    private fun onReset() {
        Log.i("GameFragment", "onReset")
    }
}