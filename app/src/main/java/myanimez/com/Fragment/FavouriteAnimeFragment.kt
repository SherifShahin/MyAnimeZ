package myanimez.com.Fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.favourite_anime_fragment.*
import myanimez.com.Adapter.FavouriteAdapter

import myanimez.com.R
import myanimez.com.ViewModel.FavouriteAnimeViewModel
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class FavouriteAnimeFragment : Fragment() {

    private lateinit var adapter: FavouriteAdapter

    private var isGridView : Boolean = true

    private lateinit var viewModel: FavouriteAnimeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favourite_anime_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = get()

        viewModel.getFavouriteAnime().observe(viewLifecycleOwner , Observer {

            if(it.isNotEmpty()) {
                favourite_anime_progressbar.visibility = View.GONE

                favourite_anime_error_layout.visibility = View.GONE

                favourite_anime_recyclerview.visibility = View.VISIBLE

                adapter = get { parametersOf(it) }

                favourite_anime_recyclerview.adapter = adapter

                favourite_anime_recyclerview.layoutManager = GridLayoutManager(context, 3)
            } else{
                favourite_anime_progressbar.visibility = View.GONE

                favourite_anime_error_layout.visibility = View.VISIBLE
            }

        })

        val toolbar = (activity as AppCompatActivity?)!!.supportActionBar!!

        toolbar.title = "Favourite"

        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_anime, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.toolbar_list_view -> {
                isGridView = !isGridView

                if(isGridView){
                    favourite_anime_recyclerview.layoutManager = GridLayoutManager(context, 3)
                    item.icon = ContextCompat.getDrawable(context!!, R.drawable.ic_list)
                }
                else{
                    favourite_anime_recyclerview.layoutManager =
                        LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
                    item.icon = ContextCompat.getDrawable(context!!, R.drawable.ic_grid)
                }

                adapter.isGridView(isGridView)

                favourite_anime_recyclerview.adapter = adapter
                true
            }

            else -> false

        }

        return super.onOptionsItemSelected(item)
    }

}
