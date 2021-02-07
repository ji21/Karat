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
import com.example.karat.databinding.FragmentSetPasswordBinding

class SetPasswordFragment : Fragment() {

    private var binding : FragmentSetPasswordBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val localBinding = FragmentSetPasswordBinding.inflate(inflater, container, false)
        binding = localBinding
        configTopAppBar()
        return localBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.setProfileBtn?.setOnClickListener {
            view.findNavController().navigate(R.id.set_profile)
        }
        var name : String = ""
        var phone : String = ""
        var birthday : String = ""
        arguments?.let {
            name = SetPasswordFragmentArgs.fromBundle(it).name
            phone = SetPasswordFragmentArgs.fromBundle(it).phone
            birthday = SetPasswordFragmentArgs.fromBundle(it).birthday
        }
        println(name)
        println(phone)
        println(birthday)
    }

    private fun configTopAppBar() {
        setHasOptionsMenu(true)
        val toolbar = (activity as AppCompatActivity).supportActionBar
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setTitle("")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requireActivity().onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}