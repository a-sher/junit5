package it.discovery.junit.shop;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderTest {

    private static Order order;

    @Test
    public void createOrder() {
        order = new Order();
        assertNotNull(order.getCreatedAt());
        assertNotNull(order.getRefId());
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    @Test
    public void startOrder_success() {
        order.addItem("Car");
        assertEquals(OrderStatus.STARTED, order.getStatus());
        assertEquals(List.of("Car"), order.getItems());
    }

    @Test
    public void completeOrder_success() {
        order.complete();
        assertEquals(OrderStatus.COMPLETED, order.getStatus());
        assertNotNull(order.getCompletedAt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cancelOrder_failure() {
        order.cancel();
    }

}
