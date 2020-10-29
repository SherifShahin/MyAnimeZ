package myanimez.com.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import myanimez.com.Fragment.ScheduleFragmentDirections
import myanimez.com.Model.Anime

import myanimez.com.R

class ScheduleDayAdapter (var list: List<Anime>)
    : RecyclerView.Adapter<ScheduleDayAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameView = itemView.findViewById<TextView>(R.id.anime_name)
        private val imageView = itemView.findViewById<ImageView>(R.id.anime_image)
        private val rating = itemView.findViewById<TextView>(R.id.anime_rating)


        fun bind(anime: Anime) {
            nameView.text = anime.title

            Glide.with(itemView).load(anime.image_url).into(imageView)

            rating.text = anime.score.toString()

            itemView.setOnClickListener {

                val action = ScheduleFragmentDirections
                    .actionScheduleFragmentToAnimeDetailsFragment(anime.mal_id)

                itemView.findNavController().navigate(action)
            }
        }
    }
}