package com.oscarSainz.pokedex.ui.pokelist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oscarSainz.pokedex.R
import com.oscarSainz.pokedex.ui.pokeinfo.PokeInfoActivity
import kotlinx.android.synthetic.main.activity_pokelist.*

class PokeListActivity : AppCompatActivity() {


    private lateinit var viewModel: PokeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokelist)

        viewModel = ViewModelProvider(this)[PokeListViewModel::class.java]


        pokelistRecyclerView.layoutManager = LinearLayoutManager(this@PokeListActivity)

        pokelistRecyclerView.adapter = PokeListAdapter{
            val intent = Intent(this@PokeListActivity, PokeInfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        viewModel.getPokemonList()


        viewModel.pokemonList.observe(this@PokeListActivity) { list ->
            (pokelistRecyclerView.adapter as PokeListAdapter).setData(list)
        }

    }

}