package myanimez.com.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import myanimez.com.Model.SearchResult
import myanimez.com.Repository.SearchRepository

class SearchViewModel(val repository: SearchRepository) : ViewModel() {

    private val searchResult : LiveData<List<SearchResult>> = repository.SearchResult

    private val RequestResult : LiveData<String> = repository.RequestResult

    fun getSearchResult() : LiveData<List<SearchResult>> = searchResult

    fun getRequestResult() : LiveData<String> = RequestResult

    fun getSearchResult(query: String){
        viewModelScope.launch {
            repository.getSearchResult(query)
        }
    }
}
