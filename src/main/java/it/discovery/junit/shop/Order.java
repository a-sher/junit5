package it.discovery.junit.shop;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.time.LocalDateTime.now;

@Getter
public class Order {
    private final UUID refId;

    private LocalDateTime createdAt;

    private LocalDateTime cancelledAt;

    private LocalDateTime completedAt;

    private OrderStatus status;

    private List<String> items;

    public Order() {
        status = OrderStatus.CREATED;
        refId = UUID.randomUUID();
        items = new ArrayList<>();
        createdAt = now();
    }

    public void addItem(String item) {
        if (status == OrderStatus.COMPLETED || status == OrderStatus.CANCELLED) {
            throw new IllegalArgumentException("Order " + refId + " can't be modified");
        }

        status = OrderStatus.STARTED;
        items.add(item);
    }

    public void cancel() {
        if (status == OrderStatus.COMPLETED) {
            throw new IllegalArgumentException("Order " + refId + " was already completed");
        }
        status = OrderStatus.CANCELLED;
        cancelledAt = now();
    }

    public void complete() {
        if (status != OrderStatus.STARTED || items.isEmpty()) {
            throw new IllegalArgumentException("Order " + refId + " is not ready to complete");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        status = OrderStatus.COMPLETED;
        completedAt = now();
    }

}
