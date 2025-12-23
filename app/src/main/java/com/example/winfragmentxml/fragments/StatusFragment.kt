package com.example.winfragmentxml.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.winfragmentxml.MainViewModel
import com.example.winfragmentxml.R
import com.example.winfragmentxml.databinding.FragmentStatusBinding

class StatusFragment : Fragment() {

    private var _binding: FragmentStatusBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        // Data Binding
        viewModel.setMessage("Hi data binding!!")

        val safeArgs: StatusFragmentArgs by navArgs()
        val name = safeArgs.name

        val nameTV = view.findViewById<TextView>(R.id.name_tv)
        nameTV.text = name
    }
}