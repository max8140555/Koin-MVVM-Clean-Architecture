package com.max.maxsamplekoin3.model.repository.inventory

import kotlinx.coroutines.flow.Flow

interface GetInventoryRepository {
    operator fun invoke(): Flow<List<RepositoryInventory>>
}