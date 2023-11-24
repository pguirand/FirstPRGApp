package com.pierretest.firstprgapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pierretest.firstprgapp.data.models.SingleDetailGameModel
import com.pierretest.firstprgapp.data.models.SingleGameModel
import com.pierretest.firstprgapp.data.network.ApiResponse
import com.pierretest.firstprgapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class gamesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _listAllGames = MutableStateFlow<ApiResponse<List<SingleGameModel>>>(ApiResponse.Loading)
    private val _singleGame = MutableStateFlow<ApiResponse<SingleDetailGameModel?>>(ApiResponse.Loading)

    val listAllGames : StateFlow<ApiResponse<List<SingleGameModel>>> = _listAllGames
    val singleGame : StateFlow<ApiResponse<SingleDetailGameModel?>> = _singleGame

    init {
        getAllGames()
    }

    fun getAllGames() {
        viewModelScope.launch {
            try {
                val gameList = repository.getAllGames()
                _listAllGames.value = gameList
            } catch (e:Exception) {
                _listAllGames.value = ApiResponse.Error(e.message)

            }
        }
    }
}