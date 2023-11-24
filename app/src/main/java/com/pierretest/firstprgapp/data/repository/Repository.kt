package com.pierretest.firstprgapp.data.repository

import com.pierretest.firstprgapp.data.database.CustomerModelEntity
import com.pierretest.firstprgapp.data.models.SingleDetailGameModel
import com.pierretest.firstprgapp.data.models.SingleGameModel
import com.pierretest.firstprgapp.data.network.ApiResponse

interface Repository {

    suspend fun getAllGames() : ApiResponse<List<SingleGameModel>>

    suspend fun getGameByCategory(category : String) : ApiResponse<List<SingleGameModel>>

    suspend fun getGameById(id : Int) : ApiResponse<SingleDetailGameModel>

    suspend fun getGameByPlatform(platform : String) : ApiResponse<List<SingleGameModel>>

    suspend fun insertCustomer(customer : CustomerModelEntity)

    suspend fun updateCustomer(customer: CustomerModelEntity)

    suspend fun deleteCustomer(customer: CustomerModelEntity)

    suspend fun getAllCustomers() : List<CustomerModelEntity>

    suspend fun getCustomerById(customerId : Long) : CustomerModelEntity?

}