package myanimez.com.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import myanimez.com.Fragment.SearchFragmentDirections
import myanimez.com.Model.SearchResult
import myanimez.com.R

class SearchAdapter (var list: List<SearchResult>)
    : RecyclerView.Adapter<SearchAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_item , parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list.get(position))

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val nameView = itemView.findViewById<TextView>(R.id.anime_name)
        private val imageView = itemView.findViewById<ImageView>(R.id.anime_image)
        private val rating = itemView.findViewById<TextView>(R.id.anime_rating)

        fun bind(anime: SearchResult) {
            nameView.text = anime.title

            Glide.with(itemView).load(anime.image_url).into(imageView)

            rating.text = anime.score.toString()

            itemView.setOnClickListener {
                val action = SearchFragmentDirections
                    .actionSearchFragmentToAnimeDetailsFragment(anime.mal_id)

                itemView.findNavController().navigate(action)
            }
        }
    }
}