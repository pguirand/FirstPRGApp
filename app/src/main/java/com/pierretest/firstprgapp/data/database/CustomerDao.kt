package com.pierretest.firstprgapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CustomerDao {

    @Insert
    suspend fun insertCustomer(customer : CustomerModelEntity)

    @Update
    suspend fun updateCustomer(customer: CustomerModelEntity)

    @Delete
    suspend fun deleteCustomer(customer: CustomerModelEntity)

    @Query("SELECT * FROM customers")
    suspend fun getAllCustomers() : List<CustomerModelEntity>

    @Query("SELECT * FROM customers WHERE customerId =:customerId")
    suspend fun getCustomerByid(customerId : Long) : CustomerModelEntity?
}