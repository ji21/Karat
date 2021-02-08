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
import com.example.karat.Global
import com.example.karat.R
import com.example.karat.databinding.FragmentCreateAccountBinding
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS


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
            val action = createAction()
            view.findNavController().navigate(action)
        }

//        if (toVerifyBtn != null) {
//            disable(toVerifyBtn, true)
//        }




        binding?.birthdayInputField?.transformIntoDatePicker(requireContext(), "MM/dd/yyyy", Date())
    }


    private fun createAction(): CreateAccountFragmentDirections.CreateAccountToVerify {
        val name = binding?.nameInputField?.text.toString()
        val phoneNumber = binding?.phoneInputField?.text.toString()
        val birthday = binding?.birthdayInputField?.text.toString()
        return CreateAccountFragmentDirections.createAccountToVerify(name, phoneNumber, birthday)
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
            val imm = getActivity()?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
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



}