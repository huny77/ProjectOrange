package com.keelim.orange.data.response


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
  @SerializedName("data1")
  var data1: Data1,
  @SerializedName("data2")
  var data2: List<Any>,
  @SerializedName("data3")
  var data3: List<Any>,
  @SerializedName("message")
  var message: String,
  @SerializedName("response")
  var response: String
) {
  data class Data1(
    @SerializedName("email")
    var email: String?,
    @SerializedName("nickname")
    var nickname: String?,
    @SerializedName("password")
    var password: String,
    @SerializedName("profileImagePath")
    var profileImagePath: String?,
    @SerializedName("role")
    var role: Any,
    @SerializedName("salt")
    var salt: Salt,
    @SerializedName("token")
    var token: Any,
    @SerializedName("userId")
    var userId: Int?,
    @SerializedName("username")
    var username: String?
  ) {
    data class Salt(
      @SerializedName("id")
      var id: Int,
      @SerializedName("salt")
      var salt: String
    )
  }
}