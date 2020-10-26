package myanimez.com.Fragment

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_anime_details.*
import myanimez.com.Adapter.AnimeCharactersAdapter
import myanimez.com.Adapter.AnimeRecommendationsAdapter
import myanimez.com.Model.AnimeCharacter
import myanimez.com.Model.AnimeDetails
import myanimez.com.Model.AnimeRecommendation

import myanimez.com.R
import myanimez.com.ViewModel.AnimeDetailsViewModel
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class AnimeDetailsFragment : Fragment() {

    private lateinit var viewModel : AnimeDetailsViewModel

    private lateinit var toolbar : ActionBar

    private lateinit var CurrentAnime : AnimeDetails

    private var Favourite : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = get()

        val id = arguments!!.getInt("id")

        viewModel.RequestAnimeDetails(id)

        toolbar = (activity as AppCompatActivity?)!!.supportActionBar!!

        toolbar.title = ""

        viewModel.getAnimeDetails().observe(viewLifecycleOwner , Observer {
            it.let {
                anime_details_progressbar.visibility = View.GONE
                anime_details_layout.visibility = View.VISIBLE
                setDataIntoUI(it)
                CurrentAnime = it
            }
        })

        viewModel.getAnimeCharacters().observe(viewLifecycleOwner, Observer {
            it.let {
                setCharacters(it)
            }
        })

        viewModel.getAnimeRecommendations().observe(viewLifecycleOwner , Observer {
            it.let {
                setRecommendnataions(it)
            }
        })

        viewModel.getRequestResult().observe(viewLifecycleOwner, Observer {
            it.let {
                anime_details_layout.visibility = View.GONE
                anime_details_error_layout.visibility = View.VISIBLE
                anime_details_progressbar.visibility = View.GONE
            }
        })

        viewModel.InFavourite(id).observe(viewLifecycleOwner , Observer {
            if(it == 1){
                Favourite = true
                anime_details_favourite_icon.setImageResource(R.drawable.ic_favourite)
            } else if(it == 0) {
                Favourite = false
                anime_details_favourite_icon.setImageResource(R.drawable.ic_favourite_border)
            }

        })

        anime_details_reload.setOnClickListener {
            anime_details_error_layout.visibility = View.GONE
            anime_details_progressbar.visibility = View.VISIBLE
            viewModel.RequestAnimeDetails(id)
        }

        anime_details_favourite.setOnClickListener {

            if(!Favourite) {
                viewModel.addToFavourite(CurrentAnime)
            } else {
                viewModel.deleteFromFavourite(CurrentAnime.mal_id)
            }
            Favourite = !Favourite
        }

    }

    private fun setRecommendnataions(it: List<AnimeRecommendation>?) {
        val adapter = get<AnimeRecommendationsAdapter> { parametersOf(it) }

        anime_details_recommendations_recycleview.adapter = adapter

        anime_details_recommendations_recycleview.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setCharacters(it: List<AnimeCharacter>?) {

        val adapter = get<AnimeCharactersAdapter> { parametersOf(it) }

        anime_details_character_recycleview.adapter = adapter

        anime_details_character_recycleview.layoutManager =  LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setDataIntoUI(it: AnimeDetails?) {

        it?.let {
            toolbar.title = it.title

            Glide.with(context!!).load(it.image_url).into(anime_details_image)

            anime_details_name.text = it.title

            anime_details_type.text = it.type

            if(it.episodes != 0)
                anime_details_episodes.text = "${it.episodes} eps"
            else
                anime_details_episodes.text = "? eps"

            anime_details_status.text = it.status

            anime_details_premiered.text = it.premiered

            anime_details_rating.text = "${it.score.toString()}/10"

            anime_details_scored_by.text = it.scored_by.toString()

            anime_details_summary.text = it.synopsis

            anime_details_source.text = it.source

            anime_details_duration.text = it.duration

            anime_details_airing.text = it.aired

            val genres = it.genres

            val inflater = LayoutInflater.from(context)

            for(genre in genres!!){

                val chip = inflater.inflate(R.layout.genre_chip_item,null,false) as Chip

                chip.text = genre.name

                anime_details_genres.addView(chip)
            }
        }
    }
}
