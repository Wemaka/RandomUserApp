package com.wemaka.randomuserapp.di

import androidx.room.Room
import com.wemaka.randomuserapp.data.data_source.AppDatabase
import com.wemaka.randomuserapp.data.data_source.RandomUserAPI
import com.wemaka.randomuserapp.data.data_source.UserDao
import com.wemaka.randomuserapp.data.repository.RandomUserRepoImpl
import com.wemaka.randomuserapp.data.util.Constants.BASE_URL
import com.wemaka.randomuserapp.domain.repository.RandomUserRepo
import com.wemaka.randomuserapp.domain.usecase.AddUser
import com.wemaka.randomuserapp.domain.usecase.DeleteUser
import com.wemaka.randomuserapp.domain.usecase.GetRandomUsers
import com.wemaka.randomuserapp.domain.usecase.GetUser
import com.wemaka.randomuserapp.domain.usecase.GetUsers
import com.wemaka.randomuserapp.presentation.createUser.CreateUserViewModel
import com.wemaka.randomuserapp.presentation.detailUser.DetailUserViewModel
import com.wemaka.randomuserapp.presentation.listUser.ListUserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

var appModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    single<UserDao> {
        get<AppDatabase>().userDao
    }

    single<RandomUserAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    single<RandomUserRepo> {
        RandomUserRepoImpl(get(), get())
    }

    singleOf(::AddUser)
    singleOf(::DeleteUser)
    singleOf(::GetRandomUsers)
    singleOf(::GetUser)
    singleOf(::GetUsers)

    viewModelOf(::CreateUserViewModel)
    viewModelOf(::DetailUserViewModel)
    viewModelOf(::ListUserViewModel)
}