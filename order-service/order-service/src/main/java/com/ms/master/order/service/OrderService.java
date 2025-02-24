package com.ms.master.order.service;
import com.ms.master.order.dto.OrderRequest;
import com.ms.master.order.model.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
public void plaseOrder(OrderRequest orderRequest);
}
