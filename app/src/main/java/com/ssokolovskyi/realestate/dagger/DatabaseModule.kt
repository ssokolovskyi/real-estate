package com.ssokolovskyi.realestate.dagger

import android.arch.persistence.room.Room
import com.ssokolovskyi.realestate.app.RealEstateApp
import com.ssokolovskyi.realestate.database.AppDatabase
import com.ssokolovskyi.realestate.database.AppDatabase.Companion.DATABASE_NAME
import com.ssokolovskyi.realestate.database.BookmarksDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
  @Singleton
  @Provides
  fun provideDatabase(app: RealEstateApp): AppDatabase {
    return Room.databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME).build()
  }

  @Provides
  fun provideBookmarksDao(database: AppDatabase): BookmarksDao {
    return database.bookmarksDao()
  }
}
