package com.example.karat.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.karat.Global
import com.example.karat.databinding.FragmentCreateAccountBinding
import com.example.karat.networkrequests.VolleySingleton
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class CreateAccountFragment : Fragment() {

    private var binding : FragmentCreateAccountBinding? = null
    private val g = Global()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val localBinding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        binding = localBinding
        configTopAppBar()
        return localBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val toVerifyBtn = binding?.toVerify

        toVerifyBtn?.setOnClickListener {
            val name = binding?.nameInputField?.text.toString()
            val phone = binding?.phoneInputField?.text.toString()
            val birthday = binding?.birthdayInputField?.text.toString()
            postForVerification(name, phone, birthday)
        }

//        if (toVerifyBtn != null) {
//            disable(toVerifyBtn, true)
//        }




        binding?.birthdayInputField?.transformIntoDatePicker(requireContext(), "yyyy-MM-dd", Date())
    }


    private fun createAction(name: String, phone: String, birthday: String): CreateAccountFragmentDirections.CreateAccountToVerify {
        return CreateAccountFragmentDirections.createAccountToVerify(name, phone, birthday)
    }



    private fun disable(view: View, yes: Boolean) {
        if (yes) {
            view.isEnabled = false
            view.isClickable = false
        } else {
            view.isEnabled = true
            view.isClickable = true
        }
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

    fun EditText.transformIntoDatePicker(context: Context, format: String, maxDate: Date? = null) {
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false

        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat(format, Locale.UK)
                setText(sdf.format(myCalendar.time))
            }

        setOnClickListener {
//            binding?.phoneInputField?.clearFocus()
            if (getActivity()?.currentFocus != null) getActivity()?.currentFocus?.clearFocus()
            hideKeyboard()
            DatePickerDialog(
                context, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                maxDate?.time?.also { datePicker.maxDate = it }
                show()
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getActivity()?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun postForVerification(name: String, phone: String, birthday: String) {
        val params = HashMap<String, String>()
        params["name"] = name
        params["phone"] = phone
        params["date_of_birth"] = birthday
        val jsonObject = JSONObject(params as Map<*, *>)
        val url = g.host + "auth/createaccount/"
        println(url)
        val request = JsonObjectRequest(
            Request.Method.POST, url ,jsonObject,
            { response ->
                // Process the json
                try {
                    println("Response: $response")
                    val action = createAction(name, phone, birthday)
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