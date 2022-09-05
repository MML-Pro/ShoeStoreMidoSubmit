package com.udacity.shoestore.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeRowLayoutBinding
import com.udacity.shoestore.models.MainViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoeListFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentShoeListBinding

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var showRowBinding: ShoeRowLayoutBinding


    private val TAG = "ShoeListFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shoe_list, container, false)

        val menuHost = requireActivity()

        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.CREATED)

        showRowBinding =
            DataBindingUtil.inflate(layoutInflater,R.layout.shoe_row_layout,null,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val testShoe1 = Shoe(
            "Test Shoe 1",

            47.0, "dummy shoe company", "Dummy shoe description"
        )
        val testShoe2 = Shoe(
            "Test Shoe 2",
            40.0, "dummy shoe company", "Dummy shoe description"
        )



        showRowBinding.newShoe = testShoe1


        mainViewModel.shoesList.observe(viewLifecycleOwner) {

            Timber.tag(TAG).i("onViewCreated: $it")

            for (shoe in it) {
                showRowBinding.newShoe = shoe

                binding.shoeListLinearLayout.addView(showRowBinding.root)
            }


        }





        binding.floatingActionButton.setOnClickListener {
            Timber.i(mainViewModel.shoesList.value?.joinToString(separator = "\n"))
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailsFragment())
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.shoe_list_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.logOut) {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            return true

        }
        return false
    }
}