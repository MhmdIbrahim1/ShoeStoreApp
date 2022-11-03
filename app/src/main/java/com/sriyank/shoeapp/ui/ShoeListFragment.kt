package com.sriyank.shoeapp.ui

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.sriyank.shoeapp.R
import com.sriyank.shoeapp.data.ShoeListData

import com.sriyank.shoeapp.databinding.FragmentShoeListBinding
import com.sriyank.shoeapp.databinding.ListViewBinding
import com.sriyank.shoeapp.util.hideKeyboard
import com.sriyank.shoeapp.viewmodels.DataViewModel


class ShoeListFragment : Fragment() {
    private  var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!

 override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        _binding = FragmentShoeListBinding.inflate(inflater,container,false)
        val viewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]

        viewModel.dataShoeList.observe(viewLifecycleOwner) { item ->
            shoeListView(item)
        }

     binding.lifecycleOwner = viewLifecycleOwner
     binding.addBtn.setOnClickListener{
        view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment) }

    }

     // Hide soft keyboard
     hideKeyboard(requireActivity())

     return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // The usage of an interface lets you inject your own implementation
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.logout_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

//create the shoe list view
    private fun shoeListView(item: List<ShoeListData>){

        item.forEach{

            val bindingView = ListViewBinding.inflate(LayoutInflater.from(requireContext()), binding.shoeListLinearLayout, false)

            with(bindingView){
                shoeCompany.text = it.shoeCompany
                shoeDescription.text = it.shoeDescription
                shoeName.text = it.shoeName
                shoeSize.text = it.shoeSize
                imageView3.setImageResource(it.images.random())
            }
            binding.shoeListLinearLayout.addView(bindingView.root)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
