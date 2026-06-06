// MongoDB Aggregation Pipeline Examples
// Run with: mongosh < aggregation-basics.js

db = db.getSiblingDB("playground");

// Sample data
db.orders.drop();
db.orders.insertMany([
    { customer: "Alice", product: "Laptop", amount: 1200, status: "completed", date: new Date("2024-01-15") },
    { customer: "Bob", product: "Phone", amount: 800, status: "completed", date: new Date("2024-01-20") },
    { customer: "Alice", product: "Tablet", amount: 500, status: "pending", date: new Date("2024-02-10") },
    { customer: "Charlie", product: "Laptop", amount: 1300, status: "completed", date: new Date("2024-02-15") },
    { customer: "Bob", product: "Headphones", amount: 150, status: "completed", date: new Date("2024-03-01") },
    { customer: "Alice", product: "Monitor", amount: 400, status: "completed", date: new Date("2024-03-10") }
]);

print("\n=== 1. Group by customer, total spending ===");
printjson(db.orders.aggregate([
    { $match: { status: "completed" } },
    { $group: { _id: "$customer", totalSpent: { $sum: "$amount" }, orderCount: { $sum: 1 } } },
    { $sort: { totalSpent: -1 } }
]).toArray());

print("\n=== 2. Monthly revenue ===");
printjson(db.orders.aggregate([
    { $match: { status: "completed" } },
    { $group: { _id: { $month: "$date" }, revenue: { $sum: "$amount" }, orders: { $sum: 1 } } },
    { $sort: { "_id": 1 } }
]).toArray());

print("\n=== 3. Product popularity ===");
printjson(db.orders.aggregate([
    { $group: { _id: "$product", timesSold: { $sum: 1 }, avgPrice: { $avg: "$amount" } } },
    { $sort: { timesSold: -1 } }
]).toArray());

print("\n=== 4. Top spender with $lookup-style enrichment ===");
printjson(db.orders.aggregate([
    { $match: { status: "completed" } },
    { $group: { _id: "$customer", total: { $sum: "$amount" }, products: { $push: "$product" } } },
    { $sort: { total: -1 } },
    { $limit: 1 }
]).toArray());
