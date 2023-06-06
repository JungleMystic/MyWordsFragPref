package com.lrm.mywordsfrag.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lrm.mywordsfrag.R
import com.lrm.mywordsfrag.fragments.LetterListFragmentDirections

class LetterAdapter():RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    private val lettersList = ('A').rangeTo('Z').toList()

    class LetterViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val letterButton: Button = view.findViewById(R.id.button_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return LetterViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return lettersList.size
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val letter = lettersList[position]
        holder.letterButton.text = letter.toString()
        holder.letterButton.setOnClickListener {
            val action = LetterListFragmentDirections.actionLetterListFragmentToWordsListFragment(letter.toString())
            holder.view.findNavController().navigate(action)
        }
    }
}