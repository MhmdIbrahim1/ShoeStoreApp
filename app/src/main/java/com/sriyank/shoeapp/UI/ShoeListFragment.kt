package com.sriyank.shoeapp.UI

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.sriyank.shoeapp.R
import com.sriyank.shoeapp.data.ShoeListData
import com.sriyank.shoeapp.databinding.FragmentShoeListBinding
import com.sriyank.shoeapp.databinding.ListViewBinding
import com.sriyank.shoeapp.viewmodels.DataViewModel

class ShoeListFragment : Fragment() {
private lateinit var binding: FragmentShoeListBinding


 override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_list,container,false)
        val viewModel = ViewModelProvider(requireActivity()).get(DataViewModel::class.java)
        setHasOptionsMenu(true)
        viewModel.dataShoeList.observe(viewLifecycleOwner,Observer{item ->
            shoeListView(item)
        })

    binding.lifecycleOwner =viewLifecycleOwner
    binding.AddNewShoe.setOnClickListener{
        view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment) }

    }
    return binding.root
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
            }
            binding.shoeListLinearLayout.addView(bindingView.root)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        return super.onOptionsItemSelected(item)
    }
}