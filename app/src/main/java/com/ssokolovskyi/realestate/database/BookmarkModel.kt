package com.ssokolovskyi.realestate.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "bookmarks")
class BookmarkModel(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "_id")
  val localId: Long = 0,

  @ColumnInfo(name = "id")
  val id: Long
)
