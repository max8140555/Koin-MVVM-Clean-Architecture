package com.max.maxsamplekoin3.model.service.inventory

import com.max.maxsamplekoin3.model.data.UserImpl
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface InventoryService {
    @GET
    fun getInventory(user: UserImpl): Flow<List<ResponseInventory>?>
}