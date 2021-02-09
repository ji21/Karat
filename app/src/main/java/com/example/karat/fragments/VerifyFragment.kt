package com.example.karat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.karat.Global
import com.example.karat.R
import com.example.karat.databinding.FragmentVerifyBinding
import com.example.karat.httprequests.VolleySingleton
import org.json.JSONObject
import java.util.HashMap


class VerifyFragment : Fragment() {


    private var binding : FragmentVerifyBinding? = null
    private val g = Global()
    var name : String = ""
    var phone : String = ""
    var birthday : String = ""

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
        arguments?.let {
            name = VerifyFragmentArgs.fromBundle(it).name
            phone = VerifyFragmentArgs.fromBundle(it).phone
            birthday = VerifyFragmentArgs.fromBundle(it).birthday
        }
        println(phone)
        binding?.verifyBtn?.setOnClickListener {
            postForConfirmation()
        }
    }

    private fun createAction(): VerifyFragmentDirections.VerifyToSetPassword {
        return VerifyFragmentDirections.verifyToSetPassword(name, phone, birthday)
    }

    private fun configTopAppBar() {
        setHasOptionsMenu(true)
        val toolbar = (activity as AppCompatActivity).supportActionBar
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setTitle("")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        println("verify fragments")
//        parentFragmentManager.popBackStack()
        requireActivity().onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun postForConfirmation() {
        val params = HashMap<String, String>()
//        params["name"] = name
        params["phone"] = phone
//        params["date_of_birth"] = birthday
        val jsonObject = JSONObject(params as Map<*, *>)
        val url = g.host + "auth/verify/"
        println(url)
        val request = JsonObjectRequest(
            Request.Method.POST, url ,jsonObject,
            { response ->
                // Process the json
                try {
                    println("Response: $response")
                    val action = createAction()
                    view?.findNavController()?.navigate(action)

                }catch (e:Exception){
                    println("Exception: $e")
                }

            }, {
                // Error in request
                println("Error: $it")
            })

        VolleySingleton.getInstance(requireActivity()).addToRequestQueue(request)
    }
}