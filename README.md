# 🎮 TUC-Tac-Toe

A graphical desktop implementation of the classic Tic-Tac-Toe game. Developed as part of the **Object-Oriented Programming (ΠΛΗ 102)** course at the **Technical University of Crete** (Spring 2022).

This project is built entirely from scratch using custom data structures (dynamic arrays, lists, trees) and algorithms—without relying on pre-built libraries for core game mechanics.

---

## 🚀 Features

The application provides a fully interactive graphical interface with multiple game modes and tracking capabilities:

### 🕹 Game Modes
* **Player vs. Player**
* **Player vs. Computer**
* **AI vs. AI**

### 🤖 Intelligent & Random AI
* **Mr. Bean:** Plays completely random moves.
* **Hal:** Plays a mathematically perfect game using a game tree and the MinMax algorithm.

### 💾 Persistent Player Profiles
* Player data is stored locally in `tuctactoe.ser` in the user’s home directory.

### 📊 Detailed Statistics
* Tracks total games, win/loss/draw records.
* Calculates overall score via a custom formula.
* Stores the 5 best games and the 5 most recent games.

### 🏆 Hall of Fame
* A leaderboard displaying the top 10 players based on their overall score.

---

## 🏗️ Architecture

The application follows a strict **Model-View-Controller (MVC)** design pattern:

* **Model:** Handles game logic, player data, and AI algorithms.
* **View:** Graphical interface built with Java Swing.
* **Controller:** Manages user input and game state updates.

### Additional Features:
* ✅ **Testing:** Core game logic is validated using Unit Tests.
* 🖥 **Graphical Interface:** Includes panel states, dialog boxes, and layouts for the game board, player stats, and Hall of Fame.

---

## 💻 Tech Stack

* **Language:** Java (JDK 8+)
* **GUI:** Java Swing
* **Paradigm:** Object-Oriented Programming (OOP)
* **Design Pattern:** MVC
* **Data Storage:** Local file (`tuctactoe.ser`)
