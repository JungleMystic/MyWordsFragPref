package com.lrm.mywordsfrag.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lrm.mywordsfrag.adapter.LetterAdapter
import com.lrm.mywordsfrag.data.SettingsDataStore
import com.lrm.mywordsfrag.databinding.FragmentLetterListBinding
import kotlinx.coroutines.launch

class LetterListFragment : Fragment() {

    private var _binding: FragmentLetterListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private var linearLayout = true

    private lateinit var layoutDataStore: SettingsDataStore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.lettersRv

        binding.gridIcon.setOnClickListener {
            linearLayout = false
            chooseLayout()
            setIcon()
            lifecycleScope.launch {
                layoutDataStore.saveLayoutMode(linearLayout, requireContext())
            }
        }

        binding.listIcon.setOnClickListener {
            linearLayout = true
            chooseLayout()
            setIcon()
            lifecycleScope.launch {
                layoutDataStore.saveLayoutMode(linearLayout, requireContext())
            }
        }

        layoutDataStore = SettingsDataStore(requireContext())
        layoutDataStore.preferenceFlow.asLiveData().observe(viewLifecycleOwner) { value ->
            linearLayout = value
            chooseLayout()
            setIcon()
        }
    }

    fun chooseLayout() {
        if (linearLayout) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        } else {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    fun setIcon() {
        if (linearLayout) {
            binding.gridIcon.visibility = View.VISIBLE
            binding.listIcon.visibility = View.INVISIBLE
        } else {
            binding.gridIcon.visibility = View.INVISIBLE
            binding.listIcon.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}