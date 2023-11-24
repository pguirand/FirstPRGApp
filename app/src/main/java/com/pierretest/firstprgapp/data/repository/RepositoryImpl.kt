package com.pierretest.firstprgapp.data.repository

import com.pierretest.firstprgapp.data.database.AppDatabase
import com.pierretest.firstprgapp.data.database.CustomerModelEntity
import com.pierretest.firstprgapp.data.models.SingleDetailGameModel
import com.pierretest.firstprgapp.data.models.SingleGameModel
import com.pierretest.firstprgapp.data.network.ApiCall
import com.pierretest.firstprgapp.data.network.ApiResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiCall: ApiCall,
    val database: AppDatabase
    ) : Repository {
    override suspend fun getAllGames(): ApiResponse<List<SingleGameModel>> {
        return try {
            ApiResponse.Success(apiCall.getAllGames())
        } catch (e:Exception) {
            ApiResponse.Error(e.message)
        }
    }

    override suspend fun getGameByCategory(category: String): ApiResponse<List<SingleGameModel>> {
        return try {
            ApiResponse.Success(apiCall.getGameByCategory(category))
        } catch (e:Exception) {
            ApiResponse.Error(e.message)
        }
    }

    override suspend fun getGameById(id: Int): ApiResponse<SingleDetailGameModel> {
        return try {
            ApiResponse.Success(apiCall.getGameById(id))
        } catch (e : Exception) {
            ApiResponse.Error(e.message)
        }
    }

    override suspend fun getGameByPlatform(platform: String): ApiResponse<List<SingleGameModel>> {
        return try {
            ApiResponse.Success(apiCall.getGameByPlatform(platform))
        } catch (e : Exception) {
            ApiResponse.Error(e.message)
        }
    }

    override suspend fun insertCustomer(customer: CustomerModelEntity) {
        database.customerDao().insertCustomer(customer)
    }

    override suspend fun updateCustomer(customer: CustomerModelEntity) {
        database.customerDao().updateCustomer(customer)
    }

    override suspend fun deleteCustomer(customer: CustomerModelEntity) {
        database.customerDao().deleteCustomer(customer)
    }

    override suspend fun getAllCustomers(): List<CustomerModelEntity> {
        return database.customerDao().getAllCustomers()
    }

    override suspend fun getCustomerById(customerId: Long): CustomerModelEntity? {
        return database.customerDao().getCustomerByid(customerId)
    }
}