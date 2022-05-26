package id.anantyan.covid19.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Covid(

	@SerializedName("missing_data")
	val missingData: Int? = null,

	@SerializedName("tanpa_provinsi")
	val tanpaProvinsi: Int? = null,

	@SerializedName("current_data")
	val currentData: Int? = null,

	@SerializedName("list_data")
	val listData: List<ListDataItem>? = null,

	@SerializedName("last_date")
	val lastDate: String? = null
) : Parcelable

@Parcelize
data class ListDataItem(

	@SerializedName("penambahan")
	val penambahan: Penambahan? = null,

	@SerializedName("doc_count")
	val docCount: Double? = null,

	@SerializedName("lokasi")
	val lokasi: Lokasi? = null,

	@SerializedName("jumlah_meninggal")
	val jumlahMeninggal: Int? = null,

	@SerializedName("kelompok_umur")
	val kelompokUmur: List<KelompokUmurItem>? = null,

	@SerializedName("jumlah_kasus")
	val jumlahKasus: Int? = null,

	@SerializedName("jumlah_sembuh")
	val jumlahSembuh: Int? = null,

	@SerializedName("jenis_kelamin")
	val jenisKelamin: List<JenisKelaminItem>? = null,

	@SerializedName("key")
	val key: String? = null,

	@SerializedName("jumlah_dirawat")
	val jumlahDirawat: Int? = null
) : Parcelable

@Parcelize
data class KelompokUmurItem(

	@SerializedName("usia")
	val usia: Usia? = null,

	@SerializedName("doc_count")
	val docCount: Int? = null,

	@SerializedName("key")
	val key: String? = null
) : Parcelable

@Parcelize
data class Penambahan(

	@SerializedName("meninggal")
	val meninggal: Int? = null,

	@SerializedName("positif")
	val positif: Int? = null,

	@SerializedName("sembuh")
	val sembuh: Int? = null
) : Parcelable

@Parcelize
data class Usia(

	@SerializedName("value")
	val value: Float? = null
) : Parcelable

@Parcelize
data class JenisKelaminItem(

	@SerializedName("doc_count")
	val docCount: Int? = null,

	@SerializedName("key")
	val key: String? = null
) : Parcelable

@Parcelize
data class Lokasi(

	@SerializedName("lon")
	val lon: Double? = null,

	@SerializedName("lat")
	val lat: Double? = null
) : Parcelable
