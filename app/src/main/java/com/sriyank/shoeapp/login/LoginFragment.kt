package com.sriyank.shoeapp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.sriyank.shoeapp.R
import com.sriyank.shoeapp.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

   private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener {
          if (userName.text.isNotEmpty()&& Password.text.isNotEmpty()) {
              val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
              findNavController().navigate(action)
          }else{
              Toast.makeText(context,"error",Toast.LENGTH_SHORT).show()
          }
        }
        binding.regBtn.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
           findNavController().navigate(action)
       }
    }

}