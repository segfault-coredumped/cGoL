# CGoL — Conway's Game of Life in Java with OpenGL

## Overview
CGoL is a Java implementation of Conway’s Game of Life using **OpenGL** for rendering. It features a flexible 2D grid system, customizable frame delays, and real-time user controls. The project demonstrates Java modular design, OpenGL rendering, and basic game loop management.

This project includes:

- `SlPingPongManager` — Handles the 2D grid and game logic.
- `GoLRenderer` — Handles OpenGL rendering of the board.
- `SlWindowManager` — Singleton for GLFW window management.
- `SlKeyStrokes` — Keyboard input manager.
- `CSC133Driver` — Main driver to launch the application.
- `SlSpot` — Configuration constants (window size, board size, frame delay).

## Features

1. **Flexible Grid Initialization**
   - Random (0 or 1)
   - Uniform values
   - Custom range

2. **Neighbor Calculations**
   - Nearest Neighbors (NN)
   - Next-Nearest Neighbors (NNN)

3. **Game Logic**
   - Classic Game of Life rules:
     - Alive cells die if they have <2 or >3 neighbors
     - Dead cells become alive if they have exactly 3 neighbors
   - Automatic update and swap of grid states

4. **OpenGL Rendering**
   - Real-time rendering using LWJGL
   - Alive cells rendered with cycling colors
   - Dead cells rendered as background color
   - Smooth color transitions for visual appeal

5. **User Controls**
   - `r` — Reset the board
   - `i` — Increase frame delay by 500 ms
   - `d` — Decrease frame delay by 500 ms
   - `p` — Pause / unpause the simulation
   - `q` — Quit the application

6. **Threaded Key Handling**
   - Key presses detected asynchronously without interrupting rendering

## Getting Started

### Requirements
- Java 8+
- Gradle
- LWJGL 3 (included via Gradle dependencies)

### Build & Run
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd CGoL
   ```
2. Run the application using Gradle wrapper:
   ```bash
   ./gradlew run      # Unix / MacOS
   gradlew.bat run    # Windows
   ```

### Controls
Once the window is open, the following keys are active:

| Key | Action |
|-----|--------|
| `r` | Reset the board |
| `i` | Increase frame delay by 500 ms |
| `d` | Decrease frame delay by 500 ms |
| `p` | Pause / unpause simulation |
| `q` | Quit the application |

## Classes

### `SlPingPongManager`
- Handles grid initialization and storage (`liveArr` and `nextArr`)
- Implements neighbor calculations (NN and NNN)
- Updates grid according to Game of Life rules

### `SlWindowManager`
- Singleton managing the GLFW window
- Handles OpenGL context and window creation

### `SlKeyStrokes`
- Handles keyboard input using GLFW callbacks
- Tracks key presses and allows event resetting

### `GoLRenderer`
- Main rendering class
- Renders each cell as a square in OpenGL
- Handles color cycling and smooth frame rendering
- Runs a separate thread for keyboard input

### `SlSpot`
- Stores configuration constants:
  - Window width & height
  - Board size
  - Frame delay

### `CSC133Driver`
- Entry point of the application
- Initializes the window, renderer, and starts the Game of Life loop

## Example Output
The board is displayed in a window with alive cells in bright colors and dead cells as the background. Colors cycle smoothly for visual effect. The console displays control instructions and feedback on frame delay and board reset.

```
+----------------------------------------------------+
|   User Controls                                    |
|   When running : r to reset                        |
|                  i to increase frame delay by 500  |
|                  d to decrease frame delay by 500  |
|                  p to pause / p to unpause         |
|                  q to quit                         |
+----------------------------------------------------+
```

## License
This project is released under the MIT License.
