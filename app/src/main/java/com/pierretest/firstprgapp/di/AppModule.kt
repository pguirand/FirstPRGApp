package com.pierretest.firstprgapp.di

import android.app.Application
import androidx.room.Room
import com.pierretest.firstprgapp.data.database.AppDatabase
import com.pierretest.firstprgapp.data.network.ApiCall
import com.pierretest.firstprgapp.data.repository.Repository
import com.pierretest.firstprgapp.data.repository.RepositoryImpl
import com.pierretest.firstprgapp.utils.ApiDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpInstance() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        client: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(
        retrofit: Retrofit
    ) : ApiCall {
        return retrofit.create(ApiCall::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        apiCall: ApiCall,
        database: AppDatabase
    ) : Repository {
        return RepositoryImpl(apiCall, database)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java, "game-database"
        ).build()
    }


}