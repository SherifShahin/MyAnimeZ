package myanimez.com.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.search_fragment.*
import myanimez.com.Adapter.SearchAdapter
import myanimez.com.R
import myanimez.com.ViewModel.SearchViewModel
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf


class SearchFragment : Fragment() {

    private lateinit var  searchview : SearchView

    private lateinit var viewModel: SearchViewModel

    private lateinit var adapter : SearchAdapter

    private lateinit var keyboard : InputMethodManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = get()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val toolbar = (activity as AppCompatActivity?)!!.supportActionBar!!

        toolbar.setTitle("Search")

        setHasOptionsMenu(true)

        viewModel.getSearchResult().observe(viewLifecycleOwner , Observer {

            search_progressbar.visibility = View.GONE

            if(it.isNotEmpty()){

                search_error_layout.visibility = View.GONE

                search_recycleview.visibility = View.VISIBLE

                adapter = get{ parametersOf(it)}

                search_recycleview.adapter = adapter

                search_recycleview.layoutManager = GridLayoutManager(context,3)

            } else {
                onErrorCase()
            }
        })

        viewModel.getRequestResult().observe(viewLifecycleOwner, Observer {
            onErrorCase()
        })

        keyboard = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    }

    private fun onErrorCase() {

        search_recycleview.visibility = View.GONE
        search_error_layout.visibility = View.VISIBLE
        search_progressbar.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val menuItem = menu.findItem(R.id.action_search)

        searchview = menuItem.actionView as SearchView

        searchview.queryHint = "anime name"

        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                search_error_layout.visibility = View.GONE

                search_recycleview.visibility = View.GONE

                search_progressbar.visibility = View.VISIBLE

                keyboard.hideSoftInputFromWindow(view!!.windowToken, 0)

                viewModel.getSearchResult(query!!)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

}
