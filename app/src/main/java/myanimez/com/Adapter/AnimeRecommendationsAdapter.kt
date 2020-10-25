package myanimez.com.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import myanimez.com.Fragment.AnimeDetailsFragmentDirections

import myanimez.com.Model.AnimeRecommendation
import myanimez.com.R

class AnimeRecommendationsAdapter (var list: List<AnimeRecommendation>)
    : RecyclerView.Adapter<AnimeRecommendationsAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_recommendation_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.findViewById<ImageView>(R.id.anime_details_recommendations_image)
        val name = itemView.findViewById<TextView>(R.id.anime_details_recommendations_name)

        fun bind(anime: AnimeRecommendation) {

            Glide.with(itemView).load(anime.image_url).into(imageView)
            name.text = anime.title

            itemView.setOnClickListener {

                val action = AnimeDetailsFragmentDirections.openDetails(anime.mal_id)

                itemView.findNavController().navigate(action)
            }
        }
    }
}