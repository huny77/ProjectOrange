package com.keelim.orange.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(
  @PrimaryKey val uid: Int?,
  @ColumnInfo(name = "keyword") val keyword: String,
  @ColumnInfo(name = "image_url") val image: String,
)
