package com.onurdemir.playground.collections;

import java.util.*;

/**
 * ArrayList vs LinkedList — insertion, access, and iteration differences.
 */
public class ListDemo {

    public static void main(String[] args) {
        System.out.println("=== ArrayList vs LinkedList ===\n");

        List<String> arrayList = new ArrayList<>(List.of("Kafka", "RabbitMQ", "Pulsar"));
        arrayList.add("NATS");
        arrayList.add(1, "Redis Streams");

        List<String> linkedList = new LinkedList<>(arrayList);
        linkedList.addFirst("ZeroMQ");
        linkedList.addLast("ActiveMQ");

        System.out.println("ArrayList: " + arrayList);
        System.out.println("LinkedList: " + linkedList);
        System.out.println("Immutable: " + List.of("one", "two", "three"));

        System.out.println("\n=== Performance: addFirst ===");
        int size = 100_000;

        List<Integer> al = new ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < size; i++) al.addFirst(i);
        long alTime = System.nanoTime() - start;

        List<Integer> ll = new LinkedList<>();
        start = System.nanoTime();
        for (int i = 0; i < size; i++) ll.addFirst(i);
        long llTime = System.nanoTime() - start;

        System.out.printf("addFirst x%d → ArrayList: %d ms, LinkedList: %d ms%n",
                size, alTime / 1_000_000, llTime / 1_000_000);

        System.out.println("\n=== Performance: random access ===");
        start = System.nanoTime();
        for (int i = 0; i < 10_000; i++) al.get(al.size() / 2);
        long alAccess = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 10_000; i++) ll.get(ll.size() / 2);
        long llAccess = System.nanoTime() - start;

        System.out.printf("get(mid) x10000 → ArrayList: %d ms, LinkedList: %d ms%n",
                alAccess / 1_000_000, llAccess / 1_000_000);
    }
}
