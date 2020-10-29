package myanimez.com.Fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.season_anime_fragment.*
import myanimez.com.Adapter.SeasonAdapter

import myanimez.com.R
import myanimez.com.ViewModel.SeasonAnimeViewModel
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class SeasonAnimeFragment : Fragment() {

    private lateinit var viewModel: SeasonAnimeViewModel

    private lateinit var adapter: SeasonAdapter

    private var isGridView : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.season_anime_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = get()

        viewModel.RequestNewData()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getSeasonAnime().observe(viewLifecycleOwner , Observer {
            if(it.isNotEmpty()) {
                LayoutView(View.GONE)
                ProgressView(View.GONE)
                season_anime_recycleview.visibility = View.VISIBLE
                adapter = get { parametersOf(it) }
                season_anime_recycleview.adapter = adapter
                season_anime_recycleview.layoutManager = GridLayoutManager(context, 3)
            }
        })

        viewModel.getRequestResult().observe(viewLifecycleOwner , Observer {
            OnErrorCase()
        })

        season_anime_reload.setOnClickListener {
            ProgressView(View.VISIBLE)
            LayoutView(View.GONE)
            viewModel.RequestNewData()
        }


        val toolbar = (activity as AppCompatActivity?)!!.supportActionBar!!

        toolbar.title = "Current Season"

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.toolbar_list_view -> {
                isGridView = !isGridView

                if(isGridView){
                    season_anime_recycleview.layoutManager = GridLayoutManager(context, 3)
                    item.icon = ContextCompat.getDrawable(context!!, R.drawable.ic_list)
                }
                else{
                    season_anime_recycleview.layoutManager =
                        LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
                    item.icon = ContextCompat.getDrawable(context!!, R.drawable.ic_grid)
                }

                adapter.isGridView(isGridView)

                season_anime_recycleview.adapter = adapter
                true
            }
            R.id.toolbar_search_view ->{
                GoToSearchView()
            }

            else -> false

        }

        return super.onOptionsItemSelected(item)
    }


    private fun LayoutView(visibility: Int) {
        season_anime_error_layout.visibility = visibility
    }

    private fun ProgressView(visibility: Int) {
        season_anime_progressbar.visibility = visibility
    }

    private fun OnErrorCase()
    {
        ProgressView(View.GONE)
        LayoutView(View.VISIBLE)
        season_anime_recycleview.visibility = View.GONE
    }

    private fun GoToSearchView(){

        val action = SeasonAnimeFragmentDirections.actionSeasonAnimeFragmentToSearchFragment()

        findNavController().navigate(action)
    }

}
