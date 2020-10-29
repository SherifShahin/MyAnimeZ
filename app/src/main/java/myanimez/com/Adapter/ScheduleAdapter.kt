package myanimez.com.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import myanimez.com.Model.Anime

import myanimez.com.R
import myanimez.com.Response.ScheduleResponse


class ScheduleAdapter (var schedule: ScheduleResponse )
    : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.schedule_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = 7

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            0 -> holder.bind(schedule.monday, "Monday")
            1 -> holder.bind(schedule.tuesday, "Tuesday")
            2 -> holder.bind(schedule.wednesday, "Wednesday")
            3 -> holder.bind(schedule.thursday, "Thursday")
            4 -> holder.bind(schedule.friday, "Friday")
            5 -> holder.bind(schedule.saturday, "Saturday")
            6 -> holder.bind(schedule.sunday, "Sunday")
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.schedule_item_name)

        val recyclerView = itemView.findViewById<RecyclerView>(R.id.schedule_item_recyclerview)

        private lateinit var adapter: ScheduleDayAdapter

        fun bind(animes: List<Anime>, day: String) {

            name.text = day

            adapter = ScheduleDayAdapter(animes)

            recyclerView.adapter = adapter

            recyclerView.layoutManager = GridLayoutManager(itemView.context, 3)
        }
    }
}