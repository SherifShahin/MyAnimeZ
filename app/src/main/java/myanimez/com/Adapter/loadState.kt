package myanimez.com.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import myanimez.com.R

class LoadStateViewHolder(
    parent: ViewGroup,
    retry: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.recycie_item_network_state, parent, false)
) {
    val progress = itemView.findViewById<ProgressBar>(R.id.progress)
    val errorMsg = itemView.findViewById<TextView>(R.id.errorMsg)

    val retryButton = itemView.findViewById<Button>(R.id.retryButton).also {
        it.setOnClickListener { retry() }
    }


    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            errorMsg.text = "sorry try again later :("
        }

        progress.isVisible = loadState is LoadState.Loading
        retryButton.isVisible = loadState is LoadState.Error
        errorMsg.isVisible = loadState is LoadState.Error
    }
}

// Adapter that displays a loading spinner when
// state = LoadState.Loading, and an error message and retry
// button when state is LoadState.Error.
class ExampleLoadStateAdapter(
    val retry: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = LoadStateViewHolder(parent,retry)

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}