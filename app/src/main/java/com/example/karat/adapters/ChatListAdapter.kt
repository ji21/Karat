package com.example.karat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.karat.R
import com.example.karat.utils.MyOnTouchListener

class ChatListAdapter(private val chatList: Array<String>) : RecyclerView.Adapter<ChatListAdapter.ViewHolder> () {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var chatItemProfile : ImageView? = null
        val chatItemContact : TextView
        val chatItemMessage : TextView

        init {
            // Define click listener for the ViewHolder's View.
            chatItemProfile = view.findViewById(R.id.chat_item_profile)
            chatItemContact = view.findViewById(R.id.chat_item_contact)
            chatItemMessage = view.findViewById(R.id.chat_item_message)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_chat, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.textView.text = dataSet[position]
        viewHolder.chatItemContact.text = chatList[position]
        viewHolder.chatItemMessage.text = "message"
        viewHolder.itemView.setOnTouchListener(MyOnTouchListener())
    }

    override fun getItemCount() = chatList.size


}