package com.max.maxsamplekoin3.model.usercase.inventory

import com.max.maxsamplekoin3.model.repository.inventory.GetInventoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetInventoryUseCaseImpl(
    private val getInventoryRepository: GetInventoryRepository
): GetInventoryUseCase {

    override fun invoke(): Flow<List<Inventory>> {
        return getInventoryRepository.invoke().map { dataList ->
            dataList.map { data ->
                Inventory(
                    orderId = data.orderId,
                    orderName = data.orderName
                )
            }
        }.flowOn(Dispatchers.Default)
    }
}