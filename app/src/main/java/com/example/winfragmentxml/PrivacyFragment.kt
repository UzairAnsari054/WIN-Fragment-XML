package com.example.winfragmentxml

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class PrivacyFragment : Fragment() {

    private val viewModel: ProfileViewModel by activityViewModels()
    private val args: PrivacyFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_privacy_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get the passed argument
        val name = args.name

        // set the argument
        val privacyTv = view.findViewById<TextView>(R.id.privacy_tv)
        privacyTv.text = name

        val saveButton = view.findViewById<Button>(R.id.privacy_save_button)
        saveButton.setOnClickListener {
            val message = "Updates the Privacy Settings"
            val resultBundle = bundleOf("privacy_bundle" to message)

            // send argument to previous Accounts fragment
            setFragmentResult("privacyMessage", resultBundle)

            // send argument to previous Profile activity
            viewModel.updatePrivacyMessage(message)

            findNavController().popBackStack()
        }
    }
}