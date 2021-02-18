package com.example.karat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.karat.R
import com.example.karat.adapters.ChatAdapter
import com.example.karat.databinding.FragmentChatListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChatListFragment : Fragment() {

    private var binding: FragmentChatListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val localBinding = FragmentChatListBinding.inflate(inflater, container, false)
        binding = localBinding

        val arr = arrayOf("omg", "abc", "gege", "omg", "abc", "gege", "omg", "abc", "gege", "omg", "abc", "gege", "omg", "abc", "gege", "omg", "abc", "gege")

        localBinding.chatRecyclerView.layoutManager = LinearLayoutManager(activity)
        localBinding.chatRecyclerView.adapter = ChatAdapter(arr)

        configTopAppBar()
        configBotNav()

        return localBinding.root
    }

    private fun configTopAppBar() {
        val toolbar = (activity as AppCompatActivity).supportActionBar
        toolbar?.setDisplayHomeAsUpEnabled(false)
        toolbar?.setTitle("Karat")
    }

    private fun configBotNav() {
        val botNav = getActivity()?.findViewById(R.id.bottom) as BottomNavigationView
        botNav.visibility = View.VISIBLE
    }
}