# 🚖 Uber Clone Backend

A robust Ride Sharing backend service built with **Spring Boot** and **MongoDB**. This application handles user authentication, ride booking, and driver dispatch workflows.

## 🚀 Tech Stack
- **Language:** Java 21+
- **Framework:** Spring Boot 3.x
- **Database:** MongoDB (Atlas)
- **Build Tool:** Maven

## ✨ Features
- **Authentication**: User Signup & Login (Passenger/Driver roles).
- **Ride Booking**: Passengers can request rides with pickup/drop locations.
- **Driver Dashboard**: Drivers can view pending ride requests.
- **Ride Matching**: Drivers can accept rides, updating status in real-time.

## 🛠️ Setup & Installation

### Prerequisites
- JDK 21 or higher
- MongoDB Atlas Connection String

### Configuration
1. Clone the repository.
2. Configure your MongoDB URI.
   - You can pass it as a command line argument or set it in `src/main/resources/application.properties` (Note: This file is git-ignored for security).
   
   **Command Line Example:**
   ```bash
   ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.data.mongodb.uri=mongodb+srv://<USER>:<PASS>@<CLUSTER>.mongodb.net/?appName=Cluster0"
   ```

### Running the App
```bash
./mvnw spring-boot:run
```
The server will start on `http://localhost:8080`.

## 📡 API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/register` | Register new user |
| POST | `/login` | Login user |

### Rides (Passenger)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/rides` | Request a new ride |

### Driver
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/driver/rides/requests` | View pending rides |
| POST | `/api/v1/driver/rides/{id}/accept` | Accept a ride |

## 🧪 Testing

### Create a Ride (Passenger)
```bash
curl -X POST http://localhost:8080/api/v1/rides \
     -u "passenger:password" \
     -H "Content-Type: application/json" \
     -d '{"pickupLocation": "Airport", "dropLocation": "Downtown"}'
```

### View Pending Rides (Driver)
```bash
curl -X GET http://localhost:8080/api/v1/driver/rides/requests \
     -u "driver:password"
```

### Accept a Ride
```bash
curl -X POST http://localhost:8080/api/v1/driver/rides/{RIDE_ID}/accept \
     -u "driver:password"
```
