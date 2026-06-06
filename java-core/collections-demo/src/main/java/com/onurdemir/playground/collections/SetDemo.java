package com.onurdemir.playground.collections;

import java.util.*;

/**
 * HashSet vs TreeSet vs LinkedHashSet — ordering and performance.
 */
public class SetDemo {

    public static void main(String[] args) {
        System.out.println("=== Set Implementations ===\n");

        Set<String> hashSet = new HashSet<>(Set.of("Java", "Kotlin", "Scala", "Groovy"));
        Set<String> treeSet = new TreeSet<>(hashSet);

        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Spring");
        linkedHashSet.add("Quarkus");
        linkedHashSet.add("Micronaut");
        linkedHashSet.add("Spring"); // duplicate ignored

        System.out.println("HashSet (unordered): " + hashSet);
        System.out.println("TreeSet (sorted): " + treeSet);
        System.out.println("LinkedHashSet (insertion order): " + linkedHashSet);

        System.out.println("\n=== Performance: contains ===");
        int size = 100_000;
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < size; i++) data.add(i);

        Set<Integer> hs = new HashSet<>(data);
        Set<Integer> ts = new TreeSet<>(data);

        long start = System.nanoTime();
        for (int i = 0; i < size; i++) hs.contains(i);
        long hsTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < size; i++) ts.contains(i);
        long tsTime = System.nanoTime() - start;

        System.out.printf("contains x%d → HashSet: %d ms, TreeSet: %d ms%n",
                size, hsTime / 1_000_000, tsTime / 1_000_000);
    }
}
