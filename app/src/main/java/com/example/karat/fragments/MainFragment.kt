package com.example.karat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.karat.Global
import com.example.karat.databinding.FragmentMainBinding
import com.example.karat.viewmodel.NotificationViewModel

class MainFragment : Fragment() {
    var binding : FragmentMainBinding? = null
    private lateinit var model : NotificationViewModel
    val g = Global()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val localBinding = FragmentMainBinding.inflate(inflater, container, false)
        binding = localBinding
        return localBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.abcedfg?.text = "hi im jeff"

        model = ViewModelProvider(this).get(com.example.karat.viewmodel.NotificationViewModel::class.java)

        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            binding?.abcedfg?.text = newName
        }

        model.test.observe(viewLifecycleOwner, nameObserver)

        model.test.value = "omg"

    }

}