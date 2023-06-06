package com.lrm.mywordsfrag.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lrm.mywordsfrag.adapter.LetterAdapter
import com.lrm.mywordsfrag.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment() {

    private var _binding: FragmentLetterListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.lettersRv

        binding.gridIcon.setOnClickListener {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
            binding.gridIcon.visibility = View.INVISIBLE
            binding.listIcon.visibility = View.VISIBLE
        }

        binding.listIcon.setOnClickListener {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.gridIcon.visibility = View.VISIBLE
            binding.listIcon.visibility = View.INVISIBLE
        }

        recyclerView.adapter = LetterAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}