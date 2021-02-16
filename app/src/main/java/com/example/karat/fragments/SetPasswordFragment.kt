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
import com.example.karat.databinding.FragmentSetPasswordBinding
import com.example.karat.networkrequests.VolleySingleton
import org.json.JSONObject
import java.util.HashMap

class SetPasswordFragment : Fragment() {

    private var binding : FragmentSetPasswordBinding? = null
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
        // Inflate the layout for this fragment
        val localBinding = FragmentSetPasswordBinding.inflate(inflater, container, false)
        binding = localBinding
        configTopAppBar()
        return localBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            name = SetPasswordFragmentArgs.fromBundle(it).name
            phone = SetPasswordFragmentArgs.fromBundle(it).phone
            birthday = SetPasswordFragmentArgs.fromBundle(it).birthday
        }
        println(name)
        println(phone)
        println(birthday)
        binding?.setProfileBtn?.setOnClickListener {
            postToSetPassword()
        }
    }

    private fun postToSetPassword() {
        val params = HashMap<String, String>()
//        params["name"] = name
        params["phone"] = phone
//        params["date_of_birth"] = birthday
        params["password"] = "a123b123"
        val jsonObject = JSONObject(params as Map<*, *>)
        val url = g.host + "auth/setpassword/"
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

    private fun createAction(): SetPasswordFragmentDirections.SetPasswordToSetProfile {
        return SetPasswordFragmentDirections.setPasswordToSetProfile(name, phone, birthday)
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