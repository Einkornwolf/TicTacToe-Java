# Tic-Tac-Toe Java

A classic Tic-Tac-Toe game built with Java Swing, featuring both two-player mode and an AI opponent powered by the Minimax algorithm.

![Java](https://img.shields.io/badge/Java-23-orange)
![License](https://img.shields.io/badge/License-MIT-blue)

## Features

- **Two-Player Mode** - Play against a friend on the same computer
- **AI Mode** - Challenge an AI opponent using the Minimax algorithm
- **Simple GUI** - Clean and intuitive Java Swing interface
- **Auto Reset** - Game automatically resets after each round

## Screenshots

The game features a 600x600 pixel window with a classic 3x3 grid. Players alternate between X and O symbols.

## Requirements

- Java 23 or higher

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Einkornwolf/TicTacToe-Java.git
   cd TicTacToe-Java
   ```

2. compile and run manually:
   ```bash
   mvn package
   java -cp target/classes game.Main
   ```

## How to Play

1. Launch the game
2. **Two-Player Mode (default)**: Click on any empty cell to place your symbol. Players alternate between X and O.
3. **AI Mode**: Check the "AI" checkbox in the top-left corner to enable AI opponent. You play as O (circles), and the AI plays as X.
4. The game announces the winner or a draw and automatically resets for a new round.

### AI Implementation

The AI uses the **Minimax algorithm**, a recursive decision-making algorithm that explores all possible game states to determine the optimal move. This makes the AI almost unbeatable

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

**Finn Wolf** - [Einkornwolf](https://github.com/Einkornwolf)

---

Feel free to contribute, report issues, or suggest improvements!
