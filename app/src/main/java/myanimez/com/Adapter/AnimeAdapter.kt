package myanimez.com.Adapter

import android.text.BoringLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import myanimez.com.Model.TopAnime
import myanimez.com.R


class AnimeAdapter: PagingDataAdapter<TopAnime, AnimeAdapter.AnimeViewHolder>(diffCallback)
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

        private val diffCallback = object : DiffUtil.ItemCallback<TopAnime>() {
            override fun areItemsTheSame(oldItem: TopAnime, newItem: TopAnime): Boolean =
                oldItem.title.equals(newItem.title)

            override fun areContentsTheSame(oldItem: TopAnime, newItem: TopAnime): Boolean =
                oldItem == newItem
        }
    }

    class AnimeViewHolder(parent: ViewGroup , isGridView: Boolean) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(if(isGridView)R.layout.anime_item else R.layout.anime_item_linear, parent, false)) {


        private val nameView = itemView.findViewById<TextView>(R.id.anime_name)
        private val imageView = itemView.findViewById<ImageView>(R.id.anime_image)
        private val rating = itemView.findViewById<TextView>(R.id.anime_rating)

        var anime : TopAnime? = null

        fun bindTo(anime : TopAnime?) {
            this.anime = anime

            nameView.text = anime?.title

            Glide.with(itemView).load(anime?.image_url).into(imageView)

            rating.text = anime?.score.toString()
        }
    }
}