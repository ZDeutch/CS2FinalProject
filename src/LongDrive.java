// Long Drive by Zander Deutch
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LongDrive implements ActionListener, KeyListener {

    // Instance variables
    private final LongDriveFrontEnd window;
    private final Ball ball;
    private static final int POWER_SPEED = 10;
    private static int state;

    // Constructor
    public LongDrive() {
        state = 0;
        ball = new Ball();
        window = new LongDriveFrontEnd(ball);

        // Pass in this window as Key Listener
        window.addKeyListener(this);
    }

    // Action Performed Method
    public void actionPerformed(ActionEvent e) {

        // If the game is zoomed out
        if (state == 2) {

            // Move the ball using propagate method
            // As long as the ball is higher than initial position
            if ((int) (ball.getY()) <= Ball.STARTING_Y) {
                ball.propagateBall();
            } else {

                // Otherwise the game is over and can be restarted
                state = 3;
            }
        } else {
            // If the game just started
            if (state == 0) {
                // Then start moving the power bar
                if (!ball.getIsBarStopped()) {
                    ball.determineBarIsUp();
                    if (ball.getIsUp()) {
                        barIncrement();
                    } else {
                        barDecrement();
                    }
                }
                // Otherwise move the angle bar
            } else if(state == 1){
                if (!ball.isAngleStopped()) {
                    ball.determineAngleIsUp();
                    if (ball.isAngleUp()) {
                        angleIncrement();
                    } else {
                        angleDecrement();
                    }
                }
            }
        }

        // Repaint the window each time to update position of angle or power
        window.repaint();
    }

    // Increments the bar height
    public void barIncrement() {
        ball.setBarHeight(ball.getBarHeight() + 1);
    }

    // Decrements the bar height
    public void barDecrement() {
        ball.setBarHeight(ball.getBarHeight() - 1);
    }

    // Increments the angle height
    public void angleIncrement() {
        ball.setAngle(ball.getAngle() + 1);
    }

    // Decrements the angle height
    public void angleDecrement() {
        ball.setAngle(ball.getAngle() - 1);
    }

    // State determines what phase of the game the user is in
    // Static so it can be used by multiple classes
    public static int getState() {
        return state;
    }

    // Key Listener Methods
    @Override
    public void keyTyped(KeyEvent e) {
        // First key pressed determines the bar height
        if (state == 0) {
            ball.setIsBarStopped(true);
            window.repaint();
            state = 1;
            // Second key pressed determines the angle height
        } else if(state == 1) {
            ball.stopAngle(true);
            ball.setVelocities();
            window.repaint();
            state = 2;
            // Third key pressed resets the game to initial screen
        } else if(state == 3){
            state = 0;
            ball.initializeScreen();
        }

        // Repaint window each time to reflect user decisions
        window.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        LongDrive game = new LongDrive();
        Timer clock = new Timer(POWER_SPEED, game);
        clock.start();
    }
}

