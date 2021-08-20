package com.keelim.orange.di

import android.content.Context
import com.keelim.orange.data.api.ApiRequestFactory
import com.keelim.orange.data.datasource.LoginDataSource
import com.keelim.orange.data.db.AppDatabase
import com.keelim.orange.data.repository.LoginRepository
import com.keelim.orange.data.repository.NotificationRepository
import com.keelim.orange.data.repository.NotificationRepositoryImpl
import com.keelim.orange.data.repository.badge.BadgeRepository
import com.keelim.orange.data.repository.badge.BadgeRepositoryImpl
import com.keelim.orange.data.repository.favorite.FavoriteRepository
import com.keelim.orange.data.repository.favorite.FavoriteRepositoryImpl
import com.keelim.orange.data.repository.feed.FeedRepository
import com.keelim.orange.data.repository.feed.FeedRepositoryImpl
import com.keelim.orange.data.repository.fight.FightRepository
import com.keelim.orange.data.repository.fight.FightRepositoryImpl
import com.keelim.orange.data.repository.friends.FriendsRepository
import com.keelim.orange.data.repository.friends.FriendsRepositoryImpl
import com.keelim.orange.data.repository.history.HistoryRepository
import com.keelim.orange.data.repository.history.HistoryRepositoryImpl
import com.keelim.orange.data.repository.profile.ProfileRepository
import com.keelim.orange.data.repository.profile.ProfileRepositoryImpl
import com.keelim.orange.data.repository.search.SearchRepository
import com.keelim.orange.data.repository.search.SearchRepositoryImpl
import com.keelim.orange.data.repository.season.SeasonRepository
import com.keelim.orange.data.repository.season.SeasonRepositoryImpl
import com.keelim.orange.data.repository.season.ranking.RankingRepository
import com.keelim.orange.data.repository.season.ranking.RankingRepositoryImpl
import com.keelim.orange.data.repository.season.create.CreateRepository
import com.keelim.orange.data.repository.season.create.CreateRepositoryImpl
import com.keelim.orange.data.repository.season.other.OtherRepository
import com.keelim.orange.data.repository.season.other.OtherRepositoryImpl
import com.keelim.orange.data.repository.theme.ThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  @Singleton
  fun provideNotificationRepository(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    apiRequestFactory: ApiRequestFactory,
  ): NotificationRepository {
    return NotificationRepositoryImpl(
      dispatcher,
      apiRequestFactory
    )
  }

  @Provides
  @Singleton
  fun provideLoginRepository(
    dataSource: LoginDataSource
  ): LoginRepository {
    return LoginRepository(
      dataSource
    )
  }

  @Provides
  @Singleton
  fun provideThemeRepository(
    @ApplicationContext context: Context
  ): ThemeRepository {
    return ThemeRepository(
      context
    )
  }

  @Provides
  @Singleton
  fun provideRankingRepository(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    apiRequestFactory: ApiRequestFactory,
  ): RankingRepository {
    return RankingRepositoryImpl(
      dispatcher,
      apiRequestFactory
    )
  }

  @Provides
  @Singleton
  fun provideOtherRepository(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    apiRequestFactory: ApiRequestFactory,
  ): OtherRepository {
    return OtherRepositoryImpl(
      dispatcher,
      apiRequestFactory,
    )
  }

  @Provides
  @Singleton
  fun provideCreateRepository(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    apiRequestFactory: ApiRequestFactory,
  ): CreateRepository {
    return CreateRepositoryImpl(
      dispatcher,
      apiRequestFactory
    )
  }

  @Provides
  @Singleton
  fun provideDetailRepository(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    apiRequestFactory: ApiRequestFactory,
  ): FeedRepository {
    return FeedRepositoryImpl(
      apiRequestFactory,
      dispatcher
    )
  }

  @Provides
  @Singleton
  fun provideFriendsRepository(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    apiRequestFactory: ApiRequestFactory,
  ): FriendsRepository {
    return FriendsRepositoryImpl(
      dispatcher,
      apiRequestFactory
    )
  }

  @Provides
  @Singleton
  fun provideHistoryRepository(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    db: AppDatabase,
  ): HistoryRepository {
    return HistoryRepositoryImpl(
      dispatcher,
      db
    )
  }

  @Provides
  @Singleton
  fun provideProfileRepository(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    apiRequestFactory: ApiRequestFactory,
  ): ProfileRepository {
    return ProfileRepositoryImpl(
      dispatcher = dispatcher,
      apiRequestFactory = apiRequestFactory
    )
  }

  @Provides
  @Singleton
  fun provideBadgeRepository(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    apiRequestFactory: ApiRequestFactory,
  ): BadgeRepository {
    return BadgeRepositoryImpl(
      dispatcher = dispatcher,
      apiRequestFactory = apiRequestFactory
    )
  }

  @Provides
  @Singleton
  fun provideFavoriteRepository(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    db: AppDatabase,
  ): FavoriteRepository {
    return FavoriteRepositoryImpl(
      dispatcher = dispatcher,
      db = db
    )
  }

  @Provides
  @Singleton
  fun provideSearchRepository(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    apiRequestFactory: ApiRequestFactory,
  ): SearchRepository {
    return SearchRepositoryImpl(
      dispatcher = dispatcher,
      apiRequestFactory = apiRequestFactory
    )
  }

  @Provides
  @Singleton
  fun provideFightRepository(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    apiRequestFactory: ApiRequestFactory,
  ): FightRepository{
    return FightRepositoryImpl(
      dispatcher = dispatcher,
      apiRequestFactory = apiRequestFactory
    )
  }

  @Provides
  @Singleton
  fun provideSeasonRepository(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    apiRequestFactory: ApiRequestFactory,
  ): SeasonRepository{
    return SeasonRepositoryImpl(
      dispatcher = dispatcher,
      apiRequestFactory = apiRequestFactory
    )
  }
}
