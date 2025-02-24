package com.ms.master.order.impl;

import com.ms.master.order.client.InventoryClient;
import com.ms.master.order.dto.OrderRequest;

import com.ms.master.order.event.OrderPlacedEvent;
import com.ms.master.order.model.Order;
import com.ms.master.order.repository.OrderRepository;
import com.ms.master.order.service.OrderService;
import lombok.RequiredArgsConstructor;
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    //private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    @Override
    public void plaseOrder(OrderRequest orderRequest) {
        // map order to order object
        //1- Using Mokito
        // 2- use wiremock
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (isProductInStock) {
            System.out.println("inside if condtion--> " + orderRequest.skuCode());
            Order order = new Order();
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order).toString();
            //Send the message to kafka topic
            // OrderPlaceEvent orderPlaceEvent= new OrderPlaceEvent(order.getOrderNumber(), orderRequest.userDetails().email());
            OrderPlacedEvent orderPlaceEvent = new OrderPlacedEvent();
            orderPlaceEvent.setOrderNumber(order.getOrderNumber());/*
            orderPlaceEvent.setEmail(orderRequest.userDetails().email()); // hard codded
            orderPlaceEvent.setFirstName(orderRequest.userDetails().firstName());
            orderPlaceEvent.setLastName(orderRequest.userDetails().lastName());
*/
            orderPlaceEvent.setEmail("test@gmail.com"); // hard codded
            orderPlaceEvent.setFirstName("shri Ram ji");
            orderPlaceEvent.setLastName("Ayodhya vasi");

            System.out.println("Start : Sending OrderplanceEnt {} to kafka topic order-placed " + orderPlaceEvent);
            kafkaTemplate.send("order-placed", orderPlaceEvent);
            System.out.println("end : Sending OrderplanceEnt {} to kafka topic order-placed " + orderPlaceEvent);
        } else {
            System.out.println("inside else condtion--> " + orderRequest.skuCode());
            throw new RuntimeException("Product with skuCode : " + orderRequest.skuCode() + " not in stock");
        }
    }
}
