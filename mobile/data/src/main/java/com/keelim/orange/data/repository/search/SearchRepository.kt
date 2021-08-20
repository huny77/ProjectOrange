package com.keelim.orange.data.repository.search

import com.keelim.orange.data.model.Search2

interface SearchRepository {
  suspend fun search(query: String): List<Search2>
}
