package com.onurdemir.playground.concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Virtual Threads (Java 21) — lightweight threads for high-throughput concurrent IO.
 */
public class VirtualThreadDemo {

    public static void main(String[] args) throws Exception {
        int taskCount = 100_000;

        System.out.println("=== Virtual Threads vs Platform Threads ===\n");

        // Virtual threads
        Instant start = Instant.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, taskCount).forEach(i ->
                    executor.submit(() -> {
                        Thread.sleep(Duration.ofMillis(10));
                        return i;
                    })
            );
        }
        long virtualTime = Duration.between(start, Instant.now()).toMillis();
        System.out.printf("Virtual threads (%d tasks): %d ms%n", taskCount, virtualTime);

        // Platform threads (limited pool)
        int poolSize = 200;
        start = Instant.now();
        try (var executor = Executors.newFixedThreadPool(poolSize)) {
            IntStream.range(0, taskCount).forEach(i ->
                    executor.submit(() -> {
                        Thread.sleep(Duration.ofMillis(10));
                        return i;
                    })
            );
        }
        long platformTime = Duration.between(start, Instant.now()).toMillis();
        System.out.printf("Platform threads (%d pool, %d tasks): %d ms%n", poolSize, taskCount, platformTime);

        System.out.printf("%nVirtual threads are ~%.1fx faster for IO-bound work%n",
                (double) platformTime / virtualTime);
    }
}
