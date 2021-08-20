package com.keelim.orange.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
  @PrimaryKey
  val ranking_title: String,
  val ranking_description: String,
  val image_address: String,
  val rank: Int,
  var isFull: Boolean = false,
)
