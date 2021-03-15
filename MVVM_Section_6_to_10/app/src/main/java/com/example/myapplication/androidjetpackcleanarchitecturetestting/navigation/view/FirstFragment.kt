package com.example.myapplication.androidjetpackcleanarchitecturetestting.navigation.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_first, container, false)
        binding.button.setOnClickListener {
            if(!TextUtils.isEmpty(binding.editTextTextPersonName2.text.toString())){
                val bundle: Bundle = bundleOf("user_input" to binding.editTextTextPersonName2.text.toString())
                it.findNavController().navigate(R.id.action_firstFragment_to_secondFragment,bundle)
            }else{
                Toast.makeText(activity,"Please insert your name",Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}