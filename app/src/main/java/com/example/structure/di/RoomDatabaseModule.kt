package com.example.structure.di

import android.content.Context
import androidx.room.Room
import com.example.structure.data.db.ALL_MIGRATIONS
import com.example.structure.data.db.Database
import com.example.structure.data.db.walk.WalkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): Database {
        return Room.databaseBuilder(
            appContext,
            Database::class.java,
            "PetPing.db"
        )
            .addMigrations(*ALL_MIGRATIONS)
            .build()
    }

    @Provides
    fun provideWalkDao(database : Database)  : WalkDao {
        return database.getWalkDao()
    }
}