package com.max.maxsamplekoin3.model.usercase.inventory

import kotlinx.coroutines.flow.Flow

interface GetInventoryUseCase {
    operator fun invoke(): Flow<List<Inventory>>
}