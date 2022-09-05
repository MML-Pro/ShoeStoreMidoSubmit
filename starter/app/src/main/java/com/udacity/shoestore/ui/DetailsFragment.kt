package com.udacity.shoestore.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailsBinding
import com.udacity.shoestore.models.MainViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

private const val TAG = "DetailsFragment"

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_details, container, false)

        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = viewLifecycleOwner


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainViewModel = this.mainViewModel
//        mainViewModel.createNewShoe()
//
        binding.saveButton.setOnClickListener {

            if (TextUtils.isEmpty(binding.shoeNameED.text.toString())) {
                binding.shoeNameED.error = "required text"
            } else if (TextUtils.isEmpty(binding.shoeSizeED.text.toString())) {
                binding.shoeSizeED.error = "required text"
            } else if (TextUtils.isEmpty(binding.companyNameED.text.toString())) {
                binding.companyNameED.error = "required text"
            } else if (TextUtils.isEmpty(binding.descriptionED.text.toString())) {
                binding.descriptionED.error = "required text"
            } else {

                val newShoe = Shoe(
                    name = binding.shoeNameED.text.toString(),
                    size = binding.shoeSizeED.text.toString().toDouble(),
                    company = binding.companyNameED.text.toString(),
                    description = binding.descriptionED.text.toString()
                )
                binding.newShoe = newShoe

                Timber.tag(TAG).i(newShoe.toString())

                mainViewModel.addShoe(newShoe)

            }
        }

        mainViewModel.eventCloseScreen.observe(viewLifecycleOwner) { close ->
           close?.let {
               if (it) {
                   findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment())
                   mainViewModel.onEventCloseComplete()
               }
           }
        }

    }
}