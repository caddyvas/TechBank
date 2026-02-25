package com.deep.techbank.di

import com.deep.techbank.data.remote.UsersApiService
import com.deep.techbank.data.repository.LoginRepositoryImpl
import com.deep.techbank.data.repository.UsersRepositoryImpl
import com.deep.techbank.domain.repository.LoginRepository
import com.deep.techbank.domain.repository.UsersRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://6993ffb4fade7a9ec0f3fc4a.mockapi.io/"

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    abstract fun bindUsersRepository(impl: UsersRepositoryImpl): UsersRepository

    companion object {
        @Provides
        @Singleton
        fun provideMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(moshi: Moshi): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        @Provides
        @Singleton
        fun provideUsersApiService(retrofit: Retrofit): UsersApiService {
            return retrofit.create(UsersApiService::class.java)
        }
    }
}
