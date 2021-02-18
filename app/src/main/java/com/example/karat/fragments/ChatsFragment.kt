package com.example.karat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.karat.R
import com.example.karat.adapters.ChatAdapter
import com.example.karat.databinding.FragmentChatsBinding


class ChatsFragment : Fragment() {

//    private var binding: FragmentChatsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val localBinding = FragmentChatsBinding.inflate(inflater, container, false)
//        binding = localBinding
//
//        val arr = arrayOf("omg", "abc", "gege", "omg", "abc", "gege", "omg", "abc", "gege", "omg", "abc", "gege", "omg", "abc", "gege", "omg", "abc", "gege")
//
//        localBinding.chatRecyclerView.layoutManager = LinearLayoutManager(activity)
//        localBinding.chatRecyclerView.adapter = ChatAdapter(arr)

        return localBinding.root
    }
}