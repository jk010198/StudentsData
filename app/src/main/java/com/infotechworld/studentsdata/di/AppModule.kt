package com.infotechworld.studentsdata.di

import android.app.Application
import com.infotechworld.roomdbdemo.database.StudentDataBase
import com.infotechworld.studentsdata.dao.StudentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun roomInstance(context: Application): StudentDataBase{
        return StudentDataBase.roomInstance(context)
    }

    @Provides
    @Singleton
    fun getDao(dataBase: StudentDataBase): StudentDao{
        return dataBase.contactDao()
    }

}