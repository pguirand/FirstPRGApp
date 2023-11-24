package com.pierretest.firstprgapp.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pierretest.firstprgapp.data.database.CustomerModelEntity
import com.pierretest.firstprgapp.data.network.ApiResponse
import com.pierretest.firstprgapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomersViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _insertCustomerState = MutableStateFlow<ApiResponse<Unit>>(ApiResponse.Loading)
    private val _listCustomers = MutableStateFlow<ApiResponse<List<CustomerModelEntity>>>(ApiResponse.Loading)

    val insertCustomerState : StateFlow<ApiResponse<Unit>> get()= _insertCustomerState
    val listCustomers : StateFlow<ApiResponse<List<CustomerModelEntity>>> get() = _listCustomers

    private val _deleteCustomerState = MutableStateFlow<ApiResponse<Unit>>(ApiResponse.Loading)
    val deleteCustomerState : StateFlow<ApiResponse<Unit>> = _deleteCustomerState

    fun insertCustomer(customer : CustomerModelEntity)  {
        viewModelScope.launch {
            try {
                repository.insertCustomer(customer)
                _insertCustomerState.value = ApiResponse.Success(Unit)
                getCustomers()
            } catch (e:Exception) {
                _insertCustomerState.value = ApiResponse.Error(e.message ?: "Insert failed")
                Log.e("Error", e.message.toString())
            }
            Log.d("InsertCustomer", "Flow value after insert: ${_insertCustomerState.value}")
        }
    }

    fun getCustomers() {
        viewModelScope.launch {
            try {
                val customers = repository.getAllCustomers()
                _listCustomers.value = ApiResponse.Success(customers)
            } catch (e : Exception) {
                _listCustomers.value = ApiResponse.Error(e.message ?: "Failed to fetch customers")
                Log.e("Error", e.message.toString())
            }
        }
    }

    fun deleteCustomer(customer: CustomerModelEntity) {
        viewModelScope.launch {
            try {
                repository.deleteCustomer(customer)
                _deleteCustomerState.value = ApiResponse.Success(Unit)
                getCustomers()
            } catch (e : Exception) {
                _deleteCustomerState.value = ApiResponse.Error(e.message?:"Unknown Error")
                Log.e("Error", e.message.toString())
            }
        }
    }



}