
Kirin’s Galaga Implementation 

INFO:
This is my implementation of the popular game Galaga. It is written solely in Java.

HOW TO USE:
All files are included so the game begins by running the file Game.java in the controller folder.


BASIC FEATURES:
1st - Ship can move from right to left on screen
2nd - Bottom left displays how many lives are left for the ship. Top left displays the current score.
3rd - Red and blue fighters come in a bezier style (fancy) movement before they get into formation. Red fighters come in from left
and blue fighters come in from right. Fighters move from left to right (red and blue move in opposite directions) for duration of game.
4th - Blue fighters exit formation and come towards player. The fighters move in a curved motion. They have a firewall weapon moves
in a curved motion as well. The blue fighter continues the curve until it gets back into its original position.
5th - A bullet removes a life from the player. The player has 3 lives initially. There is the option of catching a heart which allows
the user to receive one more life.
6th - Ship can fire bullets in a linear motion upwards. Hitting a red fighter once gives the player 10 points. The blue fighters
need to be hit multiple times and then they will be removed and the player will receive 10 more points.
7th - As the game goes on the difficulty increases. The enemy ships move faster and they fire more often. The blue fighters leave formation at higher speed
and shoot their weapons at a higher speed.
8th - Player wins when it has shot all of the blue and red fighters. High scores are saved to a file and are retrievable via the main menu.
9th - All of the images are sprites.
10th- Java code uses MVC model.

ADDITIONAL FEATURES:
Fighter Trajectories Movements:
I implemented my own version of a fancy movement. When the fighters enter the screen they go in a circular motion and then they enter formation. Bullets are
shot as they enter the formation. Red fighters come from one side and blue fighters come from the other side. In addition to this fancy movement the blue fighters have also
move in a circular motion in and out of formation. These curves are achieved by implementing functionality that operates along a bezier curve.

Special weapons:
-In my version of the game the blue fighters have a special weapon which is the flaming wall. Instead of simply firing bullets the blue fighters' weapon
is a wall of fire/bullets that follows the curved motion of the bezier curve.
-Another special weapon that I implemented was for the player. They can receive an extra life if they catch a red sprite shaped like a heart.

Fighters:
Blue players and red players are both a different class of fighters. Red fighters fire down bullets and are extinguished with one bullet. Blue fighters
leave formation on a bezier curve, have a different weapon and are extinguished after multiple bullets are shot at them.

Shield:
A shield falls down from the top of the screen and the player must catch it if he/she wants to use it. Once the player captures the shield the players'
ship turns green and it does not lose a life if it comes in contact with a bullet.

