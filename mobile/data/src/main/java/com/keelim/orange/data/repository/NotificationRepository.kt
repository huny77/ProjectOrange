package com.keelim.orange.data.repository

import com.keelim.orange.data.model.notification.Notification
import com.keelim.orange.data.response.notification.NotificationDeleteResponse

interface NotificationRepository {
  suspend fun getAllNotificationList(userId: Int): List<Notification>
  suspend fun deleteNoti(noti: Int): NotificationDeleteResponse
}
