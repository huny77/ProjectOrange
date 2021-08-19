package com.keelim.orange.data.api

import com.keelim.orange.data.call.ChallengeCall
import com.keelim.orange.data.call.CreateCall
import com.keelim.orange.data.call.LoginCall
import com.keelim.orange.data.call.SignCall
import com.keelim.orange.data.call.SignUpCall
import com.keelim.orange.data.call.WriteComment
import com.keelim.orange.data.model.entity.Favorite
import com.keelim.orange.data.response.FriendsResponse
import com.keelim.orange.data.response.ProfileResponse
import com.keelim.orange.data.response.ResultResponse
import com.keelim.orange.data.response.UserSampleResponse
import com.keelim.orange.data.response.auth.AuthResponse
import com.keelim.orange.data.response.badge.BadgeResponse
import com.keelim.orange.data.response.feed.CategoryResponse
import com.keelim.orange.data.response.feed.ChallengeResponse
import com.keelim.orange.data.response.notification.NotificationDeleteResponse
import com.keelim.orange.data.response.notification.NotificationResponse
import com.keelim.orange.data.response.ranking.RankingResponse
import com.keelim.orange.data.response.search.SearchResponse
import com.keelim.orange.data.response.season.ArticleCreateResponse
import com.keelim.orange.data.response.season.ArticleResponse
import com.keelim.orange.data.response.season.CommentResponse
import com.keelim.orange.data.response.season.ImageResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query

interface OrangeService {

  @Headers("Content-Type: application/json")
  @POST("other/sue/")
  fun sue(): Response<UserSampleResponse>

  @Headers("Content-Type: application/json")
  @POST("other/sue/")
  fun like(): Response<UserSampleResponse>

  @Headers("Content-Type: application/json")
  @POST("other/sue/")
  fun authenticate(): Response<UserSampleResponse>

  @Headers("Content-Type: application/json")
  @POST("other/sue/")
  fun share(): Response<UserSampleResponse>





  @POST("invite/")
  fun invite(
    @Query("uid") uid: String
  ): Response<ResultResponse>

  @GET("friends/{myid}")
  fun getFriends(
    @Query("myid") id: String
  ): Response<FriendsResponse>



  @POST
  fun getCompletedList(): List<Favorite>

  @GET("api/badge/list")
  suspend fun getAllBadgeList(): Response<BadgeResponse>

  @GET("api/badge/list/{userId}")
  suspend fun getMyBadge(
    @Path("userId") userId: Int
  ): Response<BadgeResponse>

  @GET("api/notification/select/{userId}")
  suspend fun getNotificationList(@Path("userId") userId: Int): Response<List<NotificationResponse>>

  @POST("api/user/signup")
  suspend fun signup(
    @Body call: SignUpCall
  ): Response<AuthResponse>

  @POST("api/user/login")
  suspend fun login(
    @Body call: LoginCall,
  ): Response<AuthResponse>

  @GET("api/challenge/ranking/point")
  suspend fun getPointRanking(): Response<List<RankingResponse>>

  @GET("api/challenge/ranking/startdate")
  suspend fun getStartRanking(): Response<List<RankingResponse>>

  @GET("api/category/list")
  suspend fun category(): Response<List<CategoryResponse>>

  @DELETE("/api/notification/delete/{notificationId}")
  suspend fun deleteNoti(@Path("notificationId") notificationId: Int): Response<NotificationDeleteResponse>

  @GET("api/challenge/list")
  suspend fun challengeList(): Response<List<ChallengeResponse>>


  @GET("api/challenge/search/title/{searchTitle}")
  suspend fun search(@Path(value = "searchTitle", encoded = true) query: String): Response<List<SearchResponse>>

  @POST("api/challenge/register/new-challenge")
  suspend fun createChallengeList(
    @Body call: ChallengeCall,
  ): Response<ResultResponse>


  @GET("api/user/userinfo/{userId}")
  suspend fun profile(@Path("userId") userId: Int): Response<ProfileResponse>

  @GET("api/challenge/{challengeId}")
  suspend fun detail(@Path("challengeId") uid: Int): Response<ChallengeResponse>

  @GET("api/challenge/search/user/{userId}")
  suspend fun getIngList(@Path("userId") userId: Int): Response<List<SearchResponse>>


  @GET("api/challenge/opponent/{challengeId}")
  suspend fun getOpponent(@Path("challengeId") uid: Int): Response<ChallengeResponse>

  @GET("api/article/challenge/{challengeId}")
  suspend fun article(@Path("challengeId") challenge: Int): Response<List<ArticleResponse>>

  @GET("api/image/get/article/{articleId}")
  suspend fun image(@Path("articleId") article: Int): Response<ImageResponse>

  @GET("api/comment/article/{articleId}")
  suspend fun comments(@Path("articleId") article: Int): Response<List<CommentResponse>>

  @POST("api/challenge/register/new-user")
  suspend fun sign(
    @Body call: SignCall,
  ): Response<ResultResponse>

  @POST("api/comment/create")
  suspend fun writeComment(
    @Body call: WriteComment,
  )


  @POST("api/article/create")
  suspend fun upload(
    @Body call: CreateCall,
  ): Response<ArticleCreateResponse>

  @Multipart
  @POST(" api/image/save/article")
  suspend fun imageUpload(
    @PartMap data: HashMap<String, RequestBody>,
    @Part image: MultipartBody.Part,
  )
}
