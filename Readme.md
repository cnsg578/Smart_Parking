# 🚗 Smart Parking Lot System (Core Java - OOP Based)

A console-based Smart Parking Lot backend system built using Core Java.

This project demonstrates strong Low-Level Design (LLD), Object-Oriented Programming, concurrency handling, and optimized parking spot allocation.

---

# 📌 Features

✅ Multi-floor parking lot setup  
✅ Dynamic configuration (floors & spots)  
✅ Automatic spot allocation based on vehicle type  
✅ O(1) allocation using Queue-based data structure  
✅ Vehicle check-in & check-out tracking  
✅ Fee calculation based on duration & vehicle type  
✅ Real-time availability updates  
✅ Thread-safe operations using ReentrantLock  
✅ Clean modular architecture

---

# 🧠 Functional Requirements Covered

### 1️⃣ Parking Spot Allocation
- Automatically assigns spot based on vehicle type:
    - MOTORCYCLE
    - CAR
    - BUS
- Optimized using:
    - Map<VehicleType, Queue<ParkingSpot>>

Time Complexity: **O(1)**

---

### 2️⃣ Check-In and Check-Out
- Entry time recorded at parking
- Exit time recorded at un-parking
- Unique ticket ID generated using UUID
- Active tickets stored in memory

---

### 3️⃣ Parking Fee Calculation

Fee per hour:

- Motorcycle → ₹10
- Car → ₹20
- Bus → ₹50

Uses Strategy Pattern for extensibility.

---

### 4️⃣ Real-Time Availability

- Spot removed from queue on park
- Spot added back on unpark
- Availability updated instantly

---

### 5️⃣ Concurrency Handling

Uses:

```java
ReentrantLock
```

- Ensures:

    - No double spot allocation

    - No race conditions

    - Thread-safe operations
---
# 🏗 System Architecture
### Main (User Interface)
        ↓
### ParkingLot (Core Logic)
        ↓
### Model Classes (Vehicle, Spot, Ticket)
        ↓
### Strategy Classes (Fee Calculation)

---
# Project Structure
- smartparking/
- │
- ├── Main.java
- │
- ├── model/
- │     ├── Vehicle.java
- │     ├── VehicleType.java
- │     ├── ParkingSpot.java
- │     ├── ParkingFloor.java
- │     ├── Ticket.java
- │
- ├── service/
- │     ├── ParkingLot.java
- │     ├── FeeStrategy.java
- │     ├── CarFeeStrategy.java
- │     ├── BikeFeeStrategy.java
- │     ├── BusFeeStrategy.java
- │
- └── exception/

  - └── NoSpotAvailableException.java
 
--- 
# 🚀 How to Run

### 1️⃣ Clone Repository

git clone https://github.com/yourusername/smart-parking-lot.git
---
### 2️⃣ Open in IntelliJ IDEA
- Open project folder
- Ensure JDK 17+ is configured
---
### 3️⃣ Run Main.java
#### The system will ask: 
````java
Enter number of floors:
Enter number of CAR spots:
Enter number of MOTORCYCLE spots:
Enter number of BUS spots:
````
#### After setup, interactive menu appears:
````java
1. Park Vehicle
2. Unpark Vehicle
3. Show Availability
4. Exit
````
---
# 💡 Sample Flow
### Setup
````java
Enter number of floors: 1
CAR spots: 2
MOTORCYCLE spots: 2
BUS spots: 1
````

### Park Vehicle 
````java
Enter License Plate: MH12AB1234
Enter Type: CAR
````

### System generates:
````java
Ticket ID: 9d8a3-7c82-1b...
Allocated Spot: F1_C1
````
---
# 🎯 Design Decisions
### Why Queue for Allocation?
#### Using 
````java
Map<VehicleType, Queue<ParkingSpot>>
````
#### Ensures constant-time allocation instead of scanning all spots.

---
### Why Strategy Pattern?

#### Fee calculation varies by vehicle type.
#### Allows extension without modifying core logic.

---

### Why ReentrantLock?

#### Prevents concurrent access issues in multi-threaded environment.

---

# 📊 Complexity Analysis
| Operation          | Time Complexity |
| ------------------ | --------------- |
| Park Vehicle       | O(1)            |
| Unpark Vehicle     | O(1)            |
| Availability Check | O(1)            |

---
# 🏆 Skills Demonstrated

- Low-Level System Design

- Object-Oriented Programming

- SOLID Principles

- Strategy Pattern

- Concurrency Handling

- Clean Code Architecture

- Data Structure Optimization

- Production-level Thinking

---

# 📌 Current Limitations

- Data stored in memory (no persistence)

- No REST API

- No database integration

- No payment gateway

---

# 🚀 Future Enhancements

- Add database persistence

- Add REST API layer

- Add reservation system

- Add dynamic pricing

- Add nearest-floor allocation

- Add Docker support

---

# 👨‍💻 Author

### Chinmay Gaikwad
### Computer Science Engineer
### System Design & Backend Enthusiast

--- 