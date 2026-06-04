# CGoL — Conway’s Game of Life (Java + OpenGL)

## Overview
CGoL is a Java implementation of Conway’s Game of Life using OpenGL (LWJGL) for real-time rendering. The project demonstrates simulation design, modular Java architecture, and GPU-based visualization.

## Features
- Flexible grid initialization (random, uniform, custom ranges)
- Neighbor calculation models (NN and NNN)
- Classic Game of Life rules implementation
- Real-time OpenGL rendering using LWJGL
- Smooth color cycling for live cells
- Interactive controls for runtime simulation management
- Multithreaded input handling for responsive interaction

## Controls
- `r` → Reset board
- `i` → Increase frame delay
- `d` → Decrease frame delay
- `p` → Pause / unpause simulation
- `q` → Quit application

## Architecture

### Grid & Simulation
- `SlPingPongManager`
  - Maintains current and next grid states
  - Handles neighbor calculations and rule application

### Rendering
- `GoLRenderer`
  - Renders grid using OpenGL
  - Handles color animation and frame updates

### Window Management
- `SlWindowManager`
  - Singleton wrapper for GLFW window/context creation

### Input Handling
- `SlKeyStrokes`
  - Processes keyboard input via GLFW callbacks

### Configuration
- `SlSpot`
  - Stores constants (window size, grid size, frame delay)

### Entry Point
- `CSC133Driver`
  - Initializes and launches the simulation

## Requirements
- Java 8+
- Gradle
- LWJGL 3 (via Gradle dependencies)

## Run
```bash
./gradlew run   # Linux / macOS
gradlew.bat run # Windows
```

## Notes
This project focuses on simulation architecture, real-time rendering, and interactive systems design using Java and OpenGL.
