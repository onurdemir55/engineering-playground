package com.onurdemir.playground.collections;

import java.util.*;

/**
 * HashMap vs TreeMap vs LinkedHashMap — ordering, access patterns, and utility methods.
 */
public class MapDemo {

    public static void main(String[] args) {
        System.out.println("=== Map Implementations ===\n");

        Map<String, Integer> hashMap = new HashMap<>(Map.of(
                "MongoDB", 27017,
                "PostgreSQL", 5432,
                "Redis", 6379,
                "Kafka", 9092
        ));

        Map<String, Integer> treeMap = new TreeMap<>(hashMap);

        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("first", 1);
        linkedHashMap.put("second", 2);
        linkedHashMap.put("third", 3);

        System.out.println("HashMap: " + hashMap);
        System.out.println("TreeMap (sorted): " + treeMap);
        System.out.println("LinkedHashMap (ordered): " + linkedHashMap);

        System.out.println("\n=== Utility Methods ===");
        hashMap.computeIfAbsent("MySQL", key -> 3306);
        hashMap.merge("Redis", 6380, Integer::max);
        hashMap.putIfAbsent("Kafka", 9999); // won't overwrite

        System.out.println("After computeIfAbsent, merge, putIfAbsent: " + hashMap);

        System.out.println("\ngetOrDefault: " + hashMap.getOrDefault("Oracle", 1521));
        System.out.println("replaceAll ports +1:");
        hashMap.replaceAll((key, port) -> port + 1);
        System.out.println(hashMap);
    }
}
