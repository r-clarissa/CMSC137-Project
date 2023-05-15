<div align="center">
  <h1 align="center">Pandemic Hero</h1>
  <p align="center">
    Welcome to the codebase for CMSC 137 project! This README file contains all the information you need to contribute to this project.
    <br />
    <br />
    <a href="https://docs.google.com/spreadsheets/d/1aKWxqGMbyEnyWf6LY2PL20jWq92p4WXMJ-gG6z7noeg/edit?usp=sharing">Task Tracker</a>
    •
    <a href="https://www.figma.com/file/sxtUN2W2v9hoBDgicC5VZY/CMSC-137%3A-The-Pandemic-Hero-Game?node-id=0%3A1&t=1se8tmSqt8tgjxvd-1">Wireframes</a>
    •
     <a href="https://lucid.app/lucidchart/0d0bc8c3-1ffe-4e19-baac-5c1cb9abb395/edit?invitationId=inv_3be17a99-adcf-4687-8627-8297eafeb98d&page=HWEp-vi-RSFO#">UML</a>
     •
     <a href="https://drive.google.com/drive/folders/12dNzwNXSyomnyB5SFX7v0bKPeh8jNTA-?usp=sharing">GDrive</a>
    <br>
  </p>
</div>

## Getting Started
To get a local copy up and running, kindly follow these steps:

### Prerequisites
1. Java JDK 8
2. JavaFX
3. Eclipse

### Installation
1. Install `Java JDK 8` and `JavaFX`.
2. Clone the repo
```
git clone https://github.com/r-clarissa/CMSC137-Project
```
4. Import the projects in Eclipse editor.
5. Run the `Main.java` inside `src/app` folder.

## Project Specifications
Inspiration came from the pandemic game (Malahito and Arellano, 2021) presented in their CMSC 22 project. The objective of this game is to bring awareness to the public of how cooperation and coordination will help solve the pandemic crisis.

### Mechanics: 
The game is a multi-level shooting game which aims to kill the different COVID bosses. 
- Players must traverse the map to find the best path to clear the level while staying alive. Multiple players can cooperate with each other.
- Players will face challenges such as viruses which will decrease their life when touched.
- To counter these viruses, players can shoot depending on the path they are facing to kill them.
- Power ups are also present to help them in the battlefield. This powerups can be obtained when their object representation is touched. 
- Increasing level will also mean increasing level of difficulty.
- If the players cleared all the levels, they win.
- If all of them died in the game, they loss.

### Features:
1. Player
    - Can shoot weapons to the viruses.
    - Has a life bar which should be maintained, else they will die.
    - Can obtain powerups which will help increase the capabilities of player in the game.
    
2. Viruses
    - Can attack players and decrease their life
    - Increasing attack capabilities with increasing level
    - Can multiply during certain levels
    
3. Boss COVID
    - A virus which has far larger life than a regular one
    - Can shoot viruses to players
    - Can move randomly to attack players
    - If a player is attacked or touched, it can decrease its life.

4. Power Ups
    - Each power up may have only one of the following features:
    - Can make players immune for few seconds.
    - Can make players invisible to attacks of viruses in a limited time
    - Can increase their attack speed for a limited time.
    - Can give player’s life

