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
import com.example.karat.dataclass.NotificationDataClass
import com.example.karat.viewmodel.NotificationViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder

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
//            val testGson = Gson().toJson(newName, NotificationDataClass::class.java)
            println(newName)
            val testGson = GsonBuilder().create().fromJson(newName, NotificationDataClass::class.java)
            binding?.abcedfg?.text = testGson.message
            println(testGson.message)
        }

        model.test.observe(viewLifecycleOwner, nameObserver)


    }

}