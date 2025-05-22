package com.example.odev7.di

import android.content.Context
import com.example.odev7.data.ToDoDatabaseHelper
import com.example.odev7.data.repo.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideToDoDatabaseHelper(@ApplicationContext context: Context): ToDoDatabaseHelper {
        return ToDoDatabaseHelper(context)
    }

    @Provides
    @Singleton
    fun provideToDoRepository(helper: ToDoDatabaseHelper): ToDoRepository {
        return ToDoRepository(helper) // veya helper üzerinden context
    }
}
