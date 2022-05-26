package id.anantyan.covid19.data.api

import id.anantyan.covid19.model.Covid
import id.anantyan.covid19.model.Skor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidApi {
    @GET("prov.json")
    suspend fun getProvince(): Response<Covid>

    @GET("skor.json")
    suspend fun getScore(): Response<Skor>
}