package com.ms.master.inventroy.service;

import org.springframework.stereotype.Service;

@Service
public interface InventoryService {

    public boolean isInStock(String skuCode, Integer quantity);

}
