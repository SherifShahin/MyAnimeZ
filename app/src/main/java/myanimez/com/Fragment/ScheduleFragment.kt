package myanimez.com.Fragment


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_schedule.*
import myanimez.com.Adapter.ScheduleAdapter

import myanimez.com.R
import myanimez.com.ViewModel.ScheduleViewModel
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class ScheduleFragment : Fragment() {

    private lateinit var viewModel : ScheduleViewModel

    private lateinit var adapter : ScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = get()

        viewModel.RequestData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.GetSchedule().observe(viewLifecycleOwner, Observer {

            schedule_progressbar.visibility =View.GONE

            schedule_error_layout.visibility =View.GONE

            schedule_recyclerview.visibility =View.VISIBLE

            adapter = get{ parametersOf(it)}

            schedule_recyclerview.adapter = adapter

            schedule_recyclerview.layoutManager =  LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        })

        viewModel.GetRequestResult().observe(viewLifecycleOwner, Observer {

            schedule_recyclerview.visibility =View.GONE

            schedule_progressbar.visibility = View.GONE

            schedule_error_layout.visibility =View.VISIBLE

        })

        schedule_reload.setOnClickListener {

            schedule_error_layout.visibility =View.GONE

            schedule_recyclerview.visibility =View.GONE

            schedule_progressbar.visibility =View.VISIBLE

            viewModel.RequestData()
        }


        val toolbar = (activity as AppCompatActivity?)!!.supportActionBar!!

        toolbar.title = "Schedule"

        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.schedule_menu, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.schedule_toolbar_search_view ->{
               GoToSearchView()
            }

            else -> false
        }

        return super.onOptionsItemSelected(item)
    }

    private fun GoToSearchView() {

        val action = ScheduleFragmentDirections.actionScheduleFragmentToSearchFragment()
        findNavController().navigate(action)
    }


}
