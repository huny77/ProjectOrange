package com.keelim.orange.data.repository

import com.keelim.orange.data.api.ApiRequestFactory
import com.keelim.orange.data.model.notification.Notification
import com.keelim.orange.data.model.notification.NotificationType
import com.keelim.orange.data.response.notification.NotificationDeleteResponse
import com.keelim.orange.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
  @IoDispatcher private val dispatcher: CoroutineDispatcher,
  private val apiRequestFactory: ApiRequestFactory,
) : NotificationRepository {
  override suspend fun getAllNotificationList(userId: Int): List<Notification> = withContext(dispatcher) {
    val response = apiRequestFactory.retrofit.getNotificationList(
      userId = userId
    )
    if (response.isSuccessful) {
      response.body()?.mapIndexed { index, notificationResponse ->
        Notification(
          notificationResponse.notificationTitle,
          notificationResponse.notificationContent,
          NotificationType.NOTIFICATION_BASIC,
          notificationResponse.notificationId
        )
      } ?: emptyList()
    } else {
      listOf()
    }
  }

  override suspend fun deleteNoti(noti: Int): NotificationDeleteResponse = withContext(dispatcher) {
    apiRequestFactory.retrofit.deleteNoti(noti).body() ?: NotificationDeleteResponse("false")
  }
}
