package com.pierretest.firstprgapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class CustomerModelEntity(
    @PrimaryKey(autoGenerate = true)
    val customerId: Long = 0L,
    val firstName : String,
    val lastName : String,
    val email : String
)


