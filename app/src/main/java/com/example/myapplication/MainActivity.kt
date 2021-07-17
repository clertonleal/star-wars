package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.starwars.client.StarWarsClientProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val starWarsClient = StarWarsClientProvider.provideStarWarsClient()
        binding.requestPeopleButton.setOnClickListener {
            if (binding.peopleId.text.isEmpty()) {
                starWarsClient.getPeople({
                    binding.output.text = it.toString()
                }, {
                    binding.output.text = it.stackTraceToString()
                })
            } else {
                starWarsClient.getPearsonById(binding.peopleId.text.toString(), {
                    binding.output.text = it.toString()
                }, {
                    binding.output.text = it.stackTraceToString()
                })
            }
        }

        binding.requestPlanetButton.setOnClickListener {
            if (binding.planetId.text.isEmpty()) {
                starWarsClient.getPlanets({
                    binding.output.text = it.toString()
                }, {
                    binding.output.text = it.stackTraceToString()
                })
            } else {
                starWarsClient.getPlanetById(binding.planetId.text.toString(), {
                    binding.output.text = it.toString()
                }, {
                    binding.output.text = it.stackTraceToString()
                })
            }
        }

        binding.requestFilmButton.setOnClickListener {
            if (binding.filmId.text.isEmpty()) {
                starWarsClient.getFilms({
                    binding.output.text = it.toString()
                }, {
                    binding.output.text = it.stackTraceToString()
                })
            } else {
                starWarsClient.getFilmById(binding.filmId.text.toString(), {
                    binding.output.text = it.toString()
                }, {
                    binding.output.text = it.stackTraceToString()
                })
            }
        }
    }
}