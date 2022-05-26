package id.anantyan.covid19.ui.province

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.anantyan.covid19.common.Resource
import id.anantyan.covid19.model.Covid
import id.anantyan.covid19.model.DataItem
import id.anantyan.covid19.repository.CovidRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CovidRepository
) : ViewModel() {
    private var _responseGetCovid: MutableLiveData<Resource<Covid>> = MutableLiveData()
    val responseGetCovid: LiveData<Resource<Covid>> = _responseGetCovid

    fun getCovid() = CoroutineScope(Dispatchers.IO).launch {
        _responseGetCovid.postValue(Resource.Loading())
        try {
            val response = repository.getProvince()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _responseGetCovid.postValue(Resource.Success(it))
                    }
                } else {
                    response.body()?.let {
                        throw Exception("Terjadi kesalahan!")
                    }
                }
            }
        } catch(ex: Exception) {
            ex.message?.let {
                _responseGetCovid.postValue(Resource.Error(it))
            }
        }
    }
}