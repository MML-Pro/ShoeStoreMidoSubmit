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

//    private lateinit var showRowBinding: ShoeRowLayoutBinding


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



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        mainViewModel.shoes.observe(viewLifecycleOwner) {

            displayShoes(it)

            Timber.tag(TAG).i("shoesList Size: ${it.size}")


        }






        binding.floatingActionButton.setOnClickListener {
            Timber.i(mainViewModel.shoes.value?.joinToString(separator = "\n"))
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailsFragment())
        }
    }

    private fun displayShoes(shoes: List<Shoe>) {
        shoes.forEach { displayShoe(it) }
    }

    private fun displayShoe(shoe: Shoe) {
        val showRowBinding =
            ShoeRowLayoutBinding.inflate(layoutInflater)

        showRowBinding.nameLabel.text = getString(R.string.string_value, "Shoe name:", shoe.name)
        showRowBinding.companyLabel.text = getString(R.string.string_value, "Company name:", shoe.company)
        showRowBinding.sizeLabel.text = getString(R.string.double_value, "Shoe size:", shoe.size)
        showRowBinding.descLabel.text = getString(R.string.string_value, "Description:", shoe.description)

        binding.shoeListLinearLayout.addView(showRowBinding.root)
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