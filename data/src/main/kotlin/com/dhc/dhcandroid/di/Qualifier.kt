package com.dhc.dhcandroid.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Mock
