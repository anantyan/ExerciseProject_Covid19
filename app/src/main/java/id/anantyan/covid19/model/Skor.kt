package id.anantyan.covid19.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Skor(

	@SerializedName("data")
	val data: List<DataItem>? = null,

	@SerializedName("tanggal")
	val tanggal: String? = null
) : Parcelable

@Parcelize
data class DataItem(

	@SerializedName("kota")
	val kota: String? = null,

	@SerializedName("kode_prov")
	val kodeProv: String? = null,

	@SerializedName("kode_kota")
	val kodeKota: String? = null,

	@SerializedName("hasil")
	val hasil: String? = null,

	@SerializedName("prov")
	val prov: String? = null
) : Parcelable
