package myanimez.com.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import myanimez.com.Model.Anime
import myanimez.com.Model.SeasonAnime
import myanimez.com.R

class SeasonAdapter (var list: List<SeasonAnime>)
    : RecyclerView.Adapter<SeasonAdapter.ViewHolder>()
{
    private var isGridView = true

    fun isGridView(value:Boolean){
        isGridView = value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(if(isGridView)R.layout.anime_item else R.layout.anime_item_linear, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list.get(position))

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val nameView = itemView.findViewById<TextView>(R.id.anime_name)
        private val imageView = itemView.findViewById<ImageView>(R.id.anime_image)
        private val rating = itemView.findViewById<TextView>(R.id.anime_rating)


        fun bind(anime: SeasonAnime) {
            nameView.text = anime?.title

            Glide.with(itemView).load(anime?.image_url).into(imageView)

            rating.text = anime?.score.toString()
        }



    }
}