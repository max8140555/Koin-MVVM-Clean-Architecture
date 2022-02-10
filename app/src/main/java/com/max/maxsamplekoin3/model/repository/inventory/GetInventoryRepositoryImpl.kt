package com.max.maxsamplekoin3.model.repository.inventory

import com.max.maxsamplekoin3.model.data.User
import com.max.maxsamplekoin3.model.service.inventory.InventoryService
import com.max.maxsamplekoin3.model.service.inventory.ResponseInventory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetInventoryRepositoryImpl(
    private val user: User,
    private val inventoryService: InventoryService
): GetInventoryRepository {

    override operator fun invoke(): Flow<List<RepositoryInventory>> {
        // realData
//        return inventoryService.getInventory(user)
//            .map { dataList ->
//                dataList?.map { data ->
//                    RepositoryInventory(
//                        orderId = data.orderId,
//                        orderName = data.orderName
//                    )
//                } ?: emptyList()
//            }.flowOn(Dispatchers.IO)

        // mockData
        return flowOf(getRemoteData(user)).map { dataList ->
            dataList.map { data ->
                RepositoryInventory(
                    orderId = data.orderId,
                    orderName = data.orderName
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    /** remote data **/
    private fun getRemoteData(user: User): List<ResponseInventory> {
        return if (user.token == "Token_Max") {
            listOf(
                ResponseInventory("1", "東京暢遊七日卷"),
                ResponseInventory("2", "大阪暢遊七日卷"),
                ResponseInventory("3", "馬爾地夫暢遊三十日卷")
            )
        } else {
            listOf(
                ResponseInventory("11", "8元 購物金折價卷"),
                ResponseInventory("12", "850元 購物金折價卷"),
                ResponseInventory("13", "85000元 購物金折價卷")
            )
        }
    }
}