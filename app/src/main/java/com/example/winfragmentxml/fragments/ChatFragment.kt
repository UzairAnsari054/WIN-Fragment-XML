package com.example.winfragmentxml.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import com.example.winfragmentxml.ProfileActivity
import com.example.winfragmentxml.R

class ChatFragment : Fragment(), MenuProvider {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set option menu
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner)

        // navigate to sibling fragment with safe args
        val statusButton = view.findViewById<Button>(R.id.status_button)
        statusButton.setOnClickListener {
            findNavController().navigate(ChatFragmentDirections.actionChatFragmentToStatusFragment(name = "Mavia"))
        }

        // navigate to profile activity with intent + extras
        val profileButton = view.findViewById<Button>(R.id.profile_button)
        profileButton.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra("name", "uzair mavia")
            startActivity(intent)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.option_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.setting1Fragment -> {
                findNavController().navigate(R.id.setting1Fragment)
                true
            }

            R.id.setting2Fragment -> {
                findNavController().navigate(R.id.setting2Fragment)
                true
            }

            else -> false
        }
    }
}