package com.example.winfragmentxml

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.navArgs

class AccountsFragment : Fragment() {
    private val args: AccountsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accounts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get the passed argument
        val userName = args.name

        // set the argument
        val nameTv = view.findViewById<TextView>(R.id.accounts_tv)
        nameTv.text = userName

        // receive argument from Privacy fragment
        setFragmentResultListener("privacyMessage") { requestKey, bundle ->
            val message = bundle.getString("privacy_bundle")
            nameTv.text = message
        }
    }
}