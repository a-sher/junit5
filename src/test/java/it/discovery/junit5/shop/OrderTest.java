package it.discovery.junit5.shop;

import it.discovery.junit.shop.Order;
import it.discovery.junit.shop.OrderStatus;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@Timeout(value = 1200, unit = TimeUnit.MILLISECONDS)
public class OrderTest {

    Order order;

    @Test
    @org.junit.jupiter.api.Order(1)
    public void createOrder() {
        order = new Order();
        assertNotNull(order.getCreatedAt());
        assertNotNull(order.getRefId());
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void startOrder_success() {
        order.addItem("Car");
        assertEquals(OrderStatus.STARTED, order.getStatus());
        assertEquals(List.of("Car"), order.getItems());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void completeOrder_success() {
        // assertTimeout(Duration.ofMillis(600), () -> order.complete());
        order.complete();
        assertEquals(OrderStatus.COMPLETED, order.getStatus());
        assertNotNull(order.getCompletedAt());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void cancelOrder_failure() {
        assertThrows(IllegalArgumentException.class, () -> order.cancel());
    }

}
