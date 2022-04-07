package com.app.di

import com.app.employeedirectory.data.remote.api.EmployeeDirectoryApi
import com.app.employeedirectory.domain.repository.EmployeeRepository
import com.app.employeedirectory.domain.repository.EmployeeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //these two functions below will tell Dagger-Hilt how to create the objects (EmployeeDirectoryAPI and EmployeeRepository that will need to be injected into constructors
    // of the objects that required them
    @Singleton
    @Provides
    fun provideEmployeeDirectoryApi(): EmployeeDirectoryApi{
        return Retrofit.Builder()
            .baseUrl(EmployeeDirectoryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmployeeDirectoryApi::class.java)
    }

    @Singleton
    @Provides
    fun provideEmployeeRepository(api: EmployeeDirectoryApi): EmployeeRepository{
        return EmployeeRepositoryImpl(api)
    }
}