package com.example.workmanager.response

data class SearchResponse(
	val totalCount: Int? = null,
	val incompleteResults: Boolean? = null,
	val items: List<User?>? = null
)



