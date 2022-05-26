package id.anantyan.covid19.ui.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.anantyan.covid19.common.Resource
import id.anantyan.covid19.model.DataItem
import id.anantyan.covid19.repository.CovidRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val repository: CovidRepository
) : ViewModel() {
    private var _responseGetSkor: MutableLiveData<Resource<List<DataItem>>> = MutableLiveData()
    val responseGetSkor: LiveData<Resource<List<DataItem>>> = _responseGetSkor

    fun getScore(query: String) = CoroutineScope(Dispatchers.IO).launch {
        _responseGetSkor.postValue(Resource.Loading())
        try {
            val response = repository.getScore()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val each = it.data?.filter { item ->
                            query == item.prov
                        }
                        each?.let { list ->
                            _responseGetSkor.postValue(Resource.Success(list))
                        }
                    }
                } else {
                    response.body()?.let {
                        throw Exception("Terjadi kesalahan!")
                    }
                }
            }
        } catch (ex: Exception) {
            ex.message?.let {
                _responseGetSkor.postValue(Resource.Error(it))
            }
        }
    }
}