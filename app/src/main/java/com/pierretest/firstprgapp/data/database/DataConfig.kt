package com.pierretest.firstprgapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CustomerModelEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao() : CustomerDao
}