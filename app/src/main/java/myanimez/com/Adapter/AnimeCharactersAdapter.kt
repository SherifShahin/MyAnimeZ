package myanimez.com.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import myanimez.com.Model.AnimeCharacter
import myanimez.com.R

class AnimeCharactersAdapter (var list: List<AnimeCharacter>)
    : RecyclerView.Adapter<AnimeCharactersAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_character_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.findViewById<ImageView>(R.id.anime_details_character_image)
        val name = itemView.findViewById<TextView>(R.id.anime_details_character_name)
        val role = itemView.findViewById<TextView>(R.id.anime_details_character_role)

        fun bind(anime: AnimeCharacter) {

            Glide.with(itemView).load(anime.image_url).into(imageView)
            name.text = anime.name
            role.text = anime.role

        }
    }
}