package com.tmastro.canadago.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tmastro.canadago.R
import com.tmastro.canadago.database.AnimalDatabase
import com.tmastro.canadago.databinding.FragmentItemDetailBinding

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentItemDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_item_detail, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = DetailFragmentArgs.fromBundle(requireArguments())

        val dataSource = AnimalDatabase.getInstance(application).databaseDao()
        val viewModelFactory = DetailViewModelFactory(arguments.itemKey, dataSource)

        val detailViewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.detailViewModel = detailViewModel
        binding.lifecycleOwner = this

        detailViewModel.navigateToGameFragment.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    DetailFragmentDirections.actionDetailFragmentToGameDestination(arguments.itemKey))
                detailViewModel.doneNavigating()
            }


        })

        return binding.root

    }
}