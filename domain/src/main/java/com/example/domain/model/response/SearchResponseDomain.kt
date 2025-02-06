package com.example.domain.model.response

data class SearchResponseDomain(
	val searchResponseDomain: List<SearchResponseItemDomain>
)

data class SearchResponseItemDomain(
	val id: Int,
	val name: String,
	val region: String,
	val country: String,
	val lat: Double,
	val lon: Double,
	val url: String
)

