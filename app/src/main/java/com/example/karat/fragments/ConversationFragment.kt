package com.example.karat.fragments

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.view.View.GONE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.karat.R
import com.example.karat.databinding.FragmentConversationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ConversationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var binding: FragmentConversationBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        configTopAppBar()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val localBinding = FragmentConversationBinding.inflate(inflater, container, false)
        binding = localBinding

        hideBotNav()
        configTopAppBar()

        return localBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configChatInputLayout()
        binding?.chatInput?.setOnFocusChangeListener { view, hasFocus ->
//            if (hasFocus) {
                //move entire view up
//                moveConversationView()
//            } else {
//                moveConversationView(0f)
//                println("no focus")
//            }
        }
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.bell)?.setVisible(false)
        menu.findItem(R.id.profile).setVisible(false)
        menu.findItem(R.id.search).setVisible(false)
    }

    private fun moveConversationView(offset: Float) {
//        ObjectAnimator.ofFloat(binding?.abcde, "translationX", offset).apply {
//            duration = 100
//            start()
//        }
    }

    private fun configChatInputLayout() {
        val width = Resources.getSystem().displayMetrics.widthPixels * 0.8
        val chatInput = binding?.chatInput
        chatInput?.layoutParams?.width = width.toInt()
        binding?.chatInputBar?.bringToFront()
    }

    private fun hideBotNav() {
        val botNav = getActivity()?.findViewById(R.id.bottom) as BottomNavigationView
        botNav.visibility = GONE
    }

    private fun configTopAppBar() {
        setHasOptionsMenu(true)
        val toolbar = (activity as AppCompatActivity).supportActionBar
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setTitle("")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        hideKeyboard()
        binding?.chatInput?.clearFocus()
        findNavController().popBackStack()
        return super.onOptionsItemSelected(item)
    }

    private fun hideKeyboard() {
        val imm = getActivity()?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConversationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ConversationFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}