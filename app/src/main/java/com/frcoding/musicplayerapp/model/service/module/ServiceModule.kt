package com.frcoding.musicplayerapp.model.service.module

import com.frcoding.musicplayerapp.model.service.AccountService
import com.frcoding.musicplayerapp.model.service.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService
}