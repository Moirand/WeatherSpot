package com.example.data.datasource.remote.model.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("SearchResponse")
	val searchResponse: List<SearchResponseItem>
)

data class SearchResponseItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("region")
	val region: String,

	@field:SerializedName("country")
	val country: String,

	@field:SerializedName("lat")
	val lat: Double,

	@field:SerializedName("lon")
	val lon: Double,

	@field:SerializedName("url")
	val url: String
)
