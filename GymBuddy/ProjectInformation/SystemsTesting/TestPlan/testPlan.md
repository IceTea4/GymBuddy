# **GymBuddy Test Plan**

## **Kaunas Technology University**
**Faculty of Informatics**  
**Project: GymBuddy**  

### **Authors:**
- Aistis Jakutonis (IFF 3/1)
- Tautrimas Ramančionis (IFF 3/1)
- Nojus Birmanas (IFF 3/1)
- Juozas Balčikonis (IFF 3/1)

**Lecturer:** Eligijus Kiudys  

**Kaunas, 2025**  

---
## **Contents**
1. [Introduction](#introduction)
2. [Tests Scope](#tests-scope)
3. [Test Strategies](#test-strategies)
4. [Prerequisites](#prerequisites)
5. [Test Priorities](#test-priorities)
6. [Test Goals](#test-goals)
7. [Test Techniques](#test-techniques)
8. [Tests Management](#tests-management)
9. [Results](#results)
10. [Testing Environment](#testing-environment)
11. [Test Scripts](#test-scripts)
12. [Testing Schedule](#testing-schedule)

---
## **Introduction**
This document outlines the test plan for *GymBuddy*, a mobile app for Android. The goal is to ensure the app meets functional requirements, identify issues, and verify usability.  

**GymBuddy** helps users plan, track, and optimize their workouts. It allows users to:
- Create custom workout routines
- Schedule sessions in a built-in calendar
- Find workout partners based on location and preferences
- Track progress and integrate with fitness-related features

The app is being developed using **Android Studio**.

---
## **Tests Scope**
### **Acceptance Testing Covers:**
- GymBuddy mobile application (Android) **version 1.0**
- Use case models for gym-goers and fitness enthusiasts
- Validation of **functionality, usability, security, and performance**

---
## **Test Strategies**
- **Unit Testing** – Tests individual features
- **Integration Testing** – Ensures modules work together
- **Performance Testing** – Assesses speed and stability
- **Usability Testing** – Evaluates ease of use
- **Static Testing** – Reviews code, design, and documentation

---
## **Prerequisites**
- **Fully Functional App Prototype** available for testing
- **Defined Test Cases** covering major functionalities
- **Testing Environment Setup** (Android 10+)
- **Bug Tracking System** for issue documentation
- **Dedicated Testers** (2-3 team members)
- **Issue Resolution Plan** for bug fixing
- **User Feedback Collection** for usability testing

---
## **Test Priorities**
### **High Priority:**
- **Core Functionalities** (workout scheduling, planning, and data storage)
- **Usability & UX** (navigation, button placements, readability)
- **Performance & Stability** (response time, crash prevention)

### **Medium Priority:**
- **Compatibility** (different screen sizes & devices)
- **Security & Data Integrity** (handling user data & input validation)

### **Low Priority:**
- **Edge Case & Stress Testing** (handling extreme input & high loads)

---
## **Test Goals**
- Ensure all **core features work as expected**
- Verify **integration of app components**
- Test **performance and stability**
- Conduct **usability testing** to optimize UI
- Perform **static code reviews** for early issue detection

---
## **Test Techniques**
- **Unit Testing** – Testing individual features
- **Integration Testing** – Ensuring smooth interaction between components
- **Static Analysis** – Reviewing source code for potential issues
- **Performance Testing** – Measuring response time, stability, and resource usage
- **Usability Testing** – Gathering user feedback for UI/UX improvements

---
## **Tests Management**
### **Roles & Responsibilities:**
- **Quality Assurance Lead** – Oversees testing, assigns tasks
- **Main Testers** – Conduct tests, document issues
- **Product Manager** – Defines requirements, prioritizes fixes

---
## **Results**
### **Expected Testing Outcomes:**
- Verification of all intended functions
- Evaluation of system security, performance, and reliability
- Documentation of software modifications in **JIRA**

---
## **Testing Environment**
### **Software Used:**
- **Android Studio 2024**
- **xUnit (latest compatible version)**
- **Lint (Android’s static code analyzer)**
- **Android Studio Profiler (for performance analysis)**

### **Testing Workstations:**
| CPU | RAM | Storage | OS |
|------|------|---------|------|
| Apple M1 Max 3.6 GHz | 32 GB | 1 TB SSD | macOS Sequoia 15.3.1 |
| Intel Core i7 4790 3.6 GHz | 16 GB | 1 TB HDD + 225 GB SSD | Windows 10 Home |
| AMD Ryzen 5 5600X 3.7 GHz | 32 GB | 1 TB SSD | Windows 11 Pro |
| AMD Ryzen 7 3700X 4.4 GHz | 32 GB | 500 GB SSD + 2 TB HDD | Windows 11 Home |
| Intel Core i7 6600U 2.6 GHz | 16 GB | 250 GB SSD | Windows 11 Pro |

---
## **Test Scripts**
### **Test Script 1: Creating a Workout and Adding Activities**
#### **Test Steps:**
1. Navigate to **Workouts** section
2. Click **Create New Workout**
3. Enter **Workout Name** (e.g., "Upper Body Strength")
4. Click **Add Activities**
5. Select exercises from predefined list or add custom exercises
6. Define **sets, reps, weight** for each exercise
7. Click **Save** and verify it appears in the list

### **Test Script 2: Adding a Workout to the Calendar**
#### **Test Steps:**
1. Navigate to **Calendar** section
2. Click **Schedule Workout**
3. Select an existing workout
4. Choose **Date & Time**
5. Click **Confirm**
6. Verify workout is scheduled in the calendar

### **Test Script 3: User Registration**
#### **Test Steps:**
1. Open **GymBuddy**
2. Click **Sign Up**
3. Enter valid **Email, Username, Password**
4. Confirm Password
5. Click **Register**
6. Verify **confirmation email** is sent
7. Click verification link & attempt login

---
## **Testing Schedule**
| **Testing Task** | **Start Date** | **Deadline** |
|----------------|--------------|------------|
| Unit Testing | 2025-03-01 | 2025-04-01 |
| Static Testing | 2025-04-01 | 2025-04-24 |
| Integration Testing | 2025-04-24 | 2025-05-01 |
| Performance Testing | 2025-05-01 | 2025-05-19 |
| Usability Testing | 2025-05-19 | 2025-05-26 |
| Acceptance Testing | 2025-05-26 | 2025-05-31 |

---
### **Final Notes**
This test plan ensures structured and efficient testing for *GymBuddy*. The testing phases will help deliver a stable and user-friendly application. Adjustments may be made based on findings during the process.
