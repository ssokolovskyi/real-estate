package com.ssokolovskyi.realestate.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface BookmarksDao {
  @Query("SELECT * FROM bookmarks")
  fun getAll(): Flowable<List<BookmarkModel>>

  @Insert
  fun insert(data: BookmarkModel)

  @Query("DELETE FROM bookmarks WHERE id = :propertyId")
  fun delete(propertyId: Long)
}
