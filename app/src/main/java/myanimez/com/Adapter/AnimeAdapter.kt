package myanimez.com.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import myanimez.com.Model.Anime
import myanimez.com.R


class AnimeAdapter: PagingDataAdapter<Anime, AnimeAdapter.AnimeViewHolder>(diffCallback)
{
    private var isGridView = true

    fun isGridView(value:Boolean){
        isGridView = value
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        try {
            holder.bindTo(getItem(position))
        } catch (e:Exception) {
            Log.e("AnimeAdapter",e.message)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {

        return AnimeViewHolder(parent, isGridView)
    }
    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<Anime>() {
            override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean =
                oldItem.title.equals(newItem.title)

            override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean =
                oldItem == newItem
        }
    }

    class AnimeViewHolder(parent: ViewGroup , isGridView: Boolean) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(if(isGridView)R.layout.anime_item else R.layout.anime_item_linear, parent, false)) {


        private val nameView = itemView.findViewById<TextView>(R.id.anime_name)
        private val imageView = itemView.findViewById<ImageView>(R.id.anime_image)
        private val rating = itemView.findViewById<TextView>(R.id.anime_rating)

        var anime : Anime? = null

        fun bindTo(anime : Anime?) {
            this.anime = anime

            nameView.text = anime?.title

            Glide.with(itemView).load(anime?.image_url).into(imageView)

            rating.text = anime?.score.toString()
        }
    }
}