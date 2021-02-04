package com.example.karat.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.karat.R
import com.example.karat.activities.MainActivity
import com.example.karat.databinding.FragmentSetProfileBinding

class SetProfileFragment : Fragment() {
    
    private var binding : FragmentSetProfileBinding? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val localBinding = FragmentSetProfileBinding.inflate(inflater, container, false)
        binding = localBinding
        configTopAppBar()
        return localBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.toMainActivityBtn?.setOnClickListener {
            val intent = Intent(getActivity(), MainActivity::class.java)
//            val intent = Intent(MainActivity(), Activity::class.java)
            startActivity(intent)
            getActivity()?.finish()
        }
    }

    private fun configTopAppBar() {
        setHasOptionsMenu(true)
        val toolbar = (activity as AppCompatActivity).supportActionBar
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setTitle("")
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        parentFragmentManager.popBackStack()
        return super.onOptionsItemSelected(item)
    }
    
}