package com.example.winfragmentxml.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.winfragmentxml.ProfileActivity
import com.example.winfragmentxml.R

class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chatButton = view.findViewById<Button>(R.id.chat_button)
        chatButton.setOnClickListener {
            findNavController().navigate(ChatFragmentDirections.actionChatFragmentToStatusFragment(name = "Mavia"))
        }

        // navigate to sibling fragment with safe args
        val profileButton = view.findViewById<Button>(R.id.profile_button)
        profileButton.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra("name", "uzair mavia")
            startActivity(intent)
        }
    }
}