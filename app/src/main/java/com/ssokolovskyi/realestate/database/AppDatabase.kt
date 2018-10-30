package com.ssokolovskyi.realestate.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ssokolovskyi.realestate.database.AppDatabase.Companion.DATABASE_VERSION

@Database(entities = [BookmarkModel::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
  companion object {
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "app.database"
  }

  abstract fun bookmarksDao(): BookmarksDao
}
