package com.tmastro.canadago.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tmastro.canadago.R
import com.tmastro.canadago.database.AnimalDatabase
import com.tmastro.canadago.databinding.GameFragmentBinding

class GameFragment : Fragment(), AdapterView.OnItemSelectedListener {

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


        // set spinner options from array resource
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.spinner_array,
                android.R.layout.simple_spinner_dropdown_item
            ).also {
                adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = adapter
            }
        }

        binding.spinner.onItemSelectedListener = this


        val adapter = ItemListAdapter(AnimalItemListener { itemId ->
            // viewModel.onAnimalItemClicked(itemId)
            Toast.makeText(context, "${itemId}", Toast.LENGTH_LONG).show()

            viewModel.onAnimalItemClicked(itemId)

        })
        binding.itemList.adapter = adapter
        viewModel.items.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {item ->
            item?.let {
                this.findNavController().navigate(
                    GameFragmentDirections.actionGameDestinationToDetailFragment(item))
                viewModel.onDetailNavigated()
            }
        })

        return binding.root
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.i("GAMEFRAGMENT", "SELECTED: " + position.toString())
                // position 0 = All
        //          1 = found
        //          2 = !found

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.i("GAMEFRAGMENT", "Nothing S")
    }
}