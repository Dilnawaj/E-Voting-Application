# 🗳️ E-Voting System (Microservices Architecture)

A **distributed, event-driven E-Voting application** built with Spring Boot microservices and React.js frontend.  
It enables voters to securely cast votes for their favorite candidates and view live election results.

---

## ⚙️ **Tech Stack**

### ✅ Backend (Microservices)
- Java 17+
- Spring Boot
- Spring Cloud OpenFeign (inter-service communication)
- Kafka (real-time vote event streaming)
- Haxelcast Cache (caching)
- Eureka (service discovery)
- Resilience4j (circuit breaker)
- MySQL (data storage)

### ✅ Frontend
- React.js
- Context API (state management)
- HTML / CSS / JavaScript
- Axios (API calls)

---

## 📦 **Microservices Modules**

| Module | Purpose |
|-------|--------:|
| **Candidate Service** | Register and manage candidates |
| **Voter Service** | Register voters, check eligibility |
| **Vote Casting Service** | Accept votes, validate voters |
| **Vote Processor Service** | Process and forward votes |
| **Result Service** | Aggregate and display election results |
| **Notification Service** |Sending email to Candidate & Voters |

All services communicate via **Kafka topics** to ensure decoupled, scalable architecture.

---

## 📧 **Features**
- Modular microservices design for independent scaling & deployment
- Real-time vote processing with Kafka
- Redis cache for faster data retrieval
- Email notifications to voters and candidates on registration and voting
- React.js frontend for interactive dashboards
- Service discovery & fault tolerance (Eureka + Resilience4j)

---

## 🚀 **Running the Backend (microservices)**

Each service has its own Spring Boot project.  
Steps (run for each):
1. Clone the repository
2. Install dependencies & build:
   ```bash
   mvn clean install
