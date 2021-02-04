package com.example.karat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.karat.R
import com.example.karat.databinding.FragmentVerifyBinding


class VerifyFragment : Fragment() {


    private var binding : FragmentVerifyBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val localBinding = FragmentVerifyBinding.inflate(inflater, container, false)
        binding = localBinding
        configTopAppBar()
        return localBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState : Bundle?) {
        binding?.verifyBtn?.setOnClickListener {
            view.findNavController().navigate(R.id.set_password)
        }
    }

    private fun configTopAppBar() {
        setHasOptionsMenu(true)
        val toolbar = (activity as AppCompatActivity).supportActionBar
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setTitle("")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        println("verify fragments")
        parentFragmentManager.popBackStack()
        return super.onOptionsItemSelected(item)
    }
}