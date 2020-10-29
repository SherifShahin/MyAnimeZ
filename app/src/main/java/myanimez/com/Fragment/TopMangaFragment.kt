package myanimez.com.Fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.top_manga_fragment.*
import myanimez.com.Adapter.TopAdapter
import myanimez.com.Adapter.ExampleLoadStateAdapter

import myanimez.com.R
import myanimez.com.ViewModel.TopViewModel
import org.koin.android.ext.android.get

class TopMangaFragment : Fragment() {

    private lateinit var viewModel: TopViewModel

    private lateinit var toolbar: ActionBar

    private var TopType = "manga"

    private var Type = "manga"

    private lateinit var adapter: TopAdapter

    private var isGridView : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.top_manga_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = get()

        viewModel.clearData()

        adapter = get()


        top_manga_recyclerview.adapter = adapter
            .withLoadStateFooter(footer = ExampleLoadStateAdapter{ adapter.retry() })

        val layoutManager = GridLayoutManager(context, 3)

        top_manga_recyclerview.layoutManager = layoutManager

        viewModel.getTopAnimes(Type,TopType).observe(viewLifecycleOwner , Observer {
            adapter.submitData(lifecycle,it)
        })

//        viewModel.getNavigate().observe(viewLifecycleOwner, Observer {
//            it?.let {
//                if(it != 0) {
//                  //  GoToAnimeDetails(it)
//                }
//            }
//        })

        toolbar = (activity as AppCompatActivity?)!!.supportActionBar!!

        toolbar.title = "Top Manga"

        setHasOptionsMenu(true)
    }

    // in Update
//    private fun GoToAnimeDetails(it: Int) {
//        val action = TopMovieAnimeFragmentDirections
//            .actionTopMovieAnimeFragmentToAnimeDetailsFragment(it)
//        findNavController().navigate(action)
//        viewModel.getNavigate().removeObservers(viewLifecycleOwner)
//    }

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
                    top_manga_recyclerview.layoutManager = GridLayoutManager(context, 3)
                    item.icon = ContextCompat.getDrawable(context!!, R.drawable.ic_list)
                }
                else{
                    top_manga_recyclerview.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    item.icon = ContextCompat.getDrawable(context!!, R.drawable.ic_grid)
                }

                adapter.isGridView(isGridView)

                top_manga_recyclerview.adapter =
                    adapter.withLoadStateFooter(footer = ExampleLoadStateAdapter{ adapter.retry()})

                true
            }
            R.id.toolbar_search_view ->{
                GoToSearchView()
            }

            else -> {
                false
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun GoToSearchView() {
        val action = TopMangaFragmentDirections
            .actionTopMangaToSearchFragment()
        findNavController().navigate(action)
    }

    override fun onPause() {
        super.onPause()
        viewModel.resetNavigation()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearData()
    }




}