package id.anantyan.covid19.repository

import id.anantyan.covid19.data.api.CovidApi
import id.anantyan.covid19.model.Covid
import id.anantyan.covid19.model.Skor
import retrofit2.Response
import javax.inject.Inject

class CovidRepository @Inject constructor(
    private val api: CovidApi
) {

    suspend fun getProvince() = api.getProvince()

    suspend fun getScore() = api.getScore()
}