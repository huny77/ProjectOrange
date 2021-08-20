package com.keelim.orange.di

import com.keelim.orange.data.api.ApiRequestFactory
import com.keelim.orange.data.datasource.LoginDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
  @Singleton
  @Provides
  fun provideLoginDataSource(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    apiRequestFactory: ApiRequestFactory,
  ): LoginDataSource {
    return LoginDataSource(
      dispatcher,
      apiRequestFactory
    )
  }
}
