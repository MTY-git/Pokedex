package com.oscarSainz.pokedex.ui.pokeinfo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.oscarSainz.pokedex.R
import kotlinx.android.synthetic.main.activity_pokeinfo.*

class PokeInfoActivity : AppCompatActivity() {

    private lateinit var viewModel: PokeInfoViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokeinfo)

        viewModel = ViewModelProvider(this)[PokeInfoViewModel::class.java]

        val id = intent.extras?.get("id") as Int

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this) { pokemon ->

            nameTextView.text = pokemon.name
            heightText.text = "Altura: ${pokemon.height/10.0}m"
            weightText.text = "Peso: ${pokemon.weight/10.0}kg"

            Glide.with(this@PokeInfoActivity).load(pokemon.sprites.frontDefault).into(imageView)
        }

    }

}