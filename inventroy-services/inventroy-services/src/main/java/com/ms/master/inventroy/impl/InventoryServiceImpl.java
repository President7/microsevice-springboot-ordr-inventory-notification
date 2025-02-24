package com.ms.master.inventroy.impl;

import com.ms.master.inventroy.repository.InventoryRepository;
import com.ms.master.inventroy.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{
   private final InventoryRepository inventoryRepository;
    @Override
    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);
    }

}
