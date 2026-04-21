package com.restaurant.ordersystem.dto;

import com.restaurant.ordersystem.enums.PaymentType;
import java.util.List;

public class CreateOrderRequest {

    private PaymentType paymentType;
    private List<OrderItemRequest> items;

    public CreateOrderRequest() {
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
}