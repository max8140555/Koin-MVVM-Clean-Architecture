package com.max.maxsamplekoin3.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.max.maxsamplekoin3.model.usercase.inventory.GetInventoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getInventoryUseCase: GetInventoryUseCase
): ViewModel() {

    private val _inventoryItemList = MutableStateFlow<List<InventoryItem>>(listOf())
    val inventoryItemList = _inventoryItemList.asStateFlow()

    init {
        getInventory()
    }

    private fun getInventory() {
        viewModelScope.launch {
            getInventoryUseCase.invoke().map { dataList ->
                dataList.map { data ->
                    InventoryItem(
                        orderId = data.orderId,
                        orderName = data.orderName
                    )
                }
            }
                .flowOn(Dispatchers.Default)
                .collect {
                    _inventoryItemList.value = it
                }
        }
    }
}