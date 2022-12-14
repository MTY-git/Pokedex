package com.oscarSainz.pokedex.ui.pokelist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oscarSainz.pokedex.R
import com.oscarSainz.pokedex.model.api.PokeResult
import kotlinx.android.synthetic.main.card_pokemon_search.view.*

class PokeListAdapter(val pokemonClick: (Int) -> Unit): RecyclerView.Adapter<PokeListAdapter.SearchViewHolder>() {

    private var pokemonList: List<PokeResult> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<PokeResult>){
        pokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon_search, parent,false))
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]

            holder.itemView.pokemonText.text = "#${position + 1} - ${pokemon.name}"
            holder.itemView.setOnClickListener { pokemonClick(position + 1) }
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


}

//-------------------------------------------------------------------------------------------------------------//
