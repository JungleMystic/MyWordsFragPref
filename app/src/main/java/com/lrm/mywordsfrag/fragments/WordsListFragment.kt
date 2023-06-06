package com.lrm.mywordsfrag.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lrm.mywordsfrag.R
import com.lrm.mywordsfrag.adapter.WordsAdapter
import com.lrm.mywordsfrag.databinding.FragmentWordsListBinding


class WordsListFragment : Fragment() {

    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    private var _binding: FragmentWordsListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var letterId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentLabel.text = resources.getString(R.string.fragment_label, letterId)

        recyclerView = binding.wordsRv

        binding.gridIcon.setOnClickListener {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.gridIcon.visibility = View.INVISIBLE
            binding.listIcon.visibility = View.VISIBLE
        }

        binding.listIcon.setOnClickListener {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.gridIcon.visibility = View.VISIBLE
            binding.listIcon.visibility = View.INVISIBLE
        }

        recyclerView.adapter = WordsAdapter(requireContext(), letterId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}