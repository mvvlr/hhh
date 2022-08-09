package com.example.newsapp.ui.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newsapp.Prefs
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentBoardBinding
import com.example.newsapp.databinding.FragmentHomeBinding

class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BoardAdapter(requireContext(),findNavController())
        binding.view.adapter = adapter
binding.dots.setViewPager2(binding.view)
        binding.dots.setViewPager2(binding.view)
        binding.tvNext.setOnClickListener {
            Prefs(requireContext()).saveState()
            findNavController().navigate(R.id.navigation_home)
        }
    }

}
