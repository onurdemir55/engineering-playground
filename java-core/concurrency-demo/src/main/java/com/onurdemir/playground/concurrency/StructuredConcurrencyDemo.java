package com.onurdemir.playground.concurrency;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Structured concurrency patterns with virtual threads.
 */
public class StructuredConcurrencyDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("=== Structured Concurrency with Virtual Threads ===\n");

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<String> userFuture = executor.submit(() -> fetchUser(1));
            Future<String> ordersFuture = executor.submit(() -> fetchOrders(1));
            Future<String> recommendationsFuture = executor.submit(() -> fetchRecommendations(1));

            // All three run concurrently on virtual threads
            String user = userFuture.get();
            String orders = ordersFuture.get();
            String recommendations = recommendationsFuture.get();

            System.out.printf("User: %s%nOrders: %s%nRecommendations: %s%n", user, orders, recommendations);
        }

        System.out.println("\nAll tasks completed concurrently on virtual threads.");
        System.out.println("Thread type: " + Thread.currentThread());
    }

    static String fetchUser(int id) throws InterruptedException {
        Thread.sleep(Duration.ofMillis(200)); // simulate IO
        System.out.println("  fetchUser on: " + Thread.currentThread());
        return "User{id=%d, name='Onur'}".formatted(id);
    }

    static String fetchOrders(int userId) throws InterruptedException {
        Thread.sleep(Duration.ofMillis(300)); // simulate IO
        System.out.println("  fetchOrders on: " + Thread.currentThread());
        return "Orders{userId=%d, count=5}".formatted(userId);
    }

    static String fetchRecommendations(int userId) throws InterruptedException {
        Thread.sleep(Duration.ofMillis(150)); // simulate IO
        System.out.println("  fetchRecommendations on: " + Thread.currentThread());
        return "Recommendations{userId=%d, items=10}".formatted(userId);
    }
}
