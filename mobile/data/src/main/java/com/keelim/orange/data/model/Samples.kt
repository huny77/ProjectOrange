package com.keelim.orange.data.model

import com.keelim.orange.data.model.entity.History

data class Sample(
  val name: String,
  val courses: Int,
  val imageUrl: String
)

val samples: List<History> = listOf(
  Sample("Architecture", 58, "https://source.unsplash.com/RFDP7_80v5A"),
  Sample("Arts & Crafts", 121, "https://source.unsplash.com/Tq4YjCa2BSc"),
  Sample("Business", 78, "https://source.unsplash.com/MYbhN8KaaEc"),
  Sample("Culinary", 118, "https://source.unsplash.com/uB7q7aipU2o"),
  Sample("Design", 423, "https://source.unsplash.com/cXkrqY2wFyc"),
  Sample("Fashion", 92, "https://source.unsplash.com/omgRZCmTvUM"),
  Sample("Film", 165, "https://source.unsplash.com/9lTUAlNB87M"),
  Sample("Gaming", 164, "https://source.unsplash.com/xITnxxlzGAE"),
  Sample("Illustration", 326, "https://source.unsplash.com/lxoq0zppH5w"),
  Sample("Lifestyle", 305, "https://source.unsplash.com/DzIt-fTYv4E"),
  Sample("Music", 212, "https://source.unsplash.com/LoppUA_9F1w"),
  Sample("Painting", 172, "https://source.unsplash.com/FwF_fKj5tBo"),
  Sample("Photography", 321, "https://source.unsplash.com/8nXKXYdO-Wk"),
  Sample("Technology", 118, "https://source.unsplash.com/MxVkWPiJALs")
).map {
  History(
    it.courses,
    it.name,
    it.imageUrl
  )
}
