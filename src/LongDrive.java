import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LongDrive implements ActionListener, KeyListener {
    private LongDriveFrontEnd window;
    private final Ball ball;
    private static final int POWER_SPEED = 10;
    private static final int ANGLE_SPEED = 50;
    private static int state;

    public LongDrive() {
        state = 0;
        ball = new Ball();
        window = new LongDriveFrontEnd(this, ball);
        window.addKeyListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (state == 2) {
            if ((int) (ball.getY()) <= Ball.STARTING_Y) {
                System.out.println("Y Value is :" + ball.getY());
                System.out.println("Starting Y Value is :" + ball.STARTING_Y);
                ball.propagateBall();
            } else {
                state = 3;
            }
        } else {
            if (state == 0) {
                if (!ball.getIsBarStopped()) {
                    ball.determineBarIsUp();
                    if (ball.getIsUp()) {
                        barIncrement();
                    } else {
                        barDecrement();
                    }
                }
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
        window.repaint();
    }

    public void barIncrement() {
        ball.setBarHeight(ball.getBarHeight() + 1);
    }

    public void barDecrement() {
        ball.setBarHeight(ball.getBarHeight() - 1);
    }

    public void angleIncrement() {
        ball.setAngle(ball.getAngle() + 1);
    }

    public void angleDecrement() {
        ball.setAngle(ball.getAngle() - 1);
    }

    public boolean isZoomedInScreen() {
        return state == 0 || state == 1;
    }

    public static int getState() {
        return state;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (state == 0) {
            ball.setIsBarStopped(true);
            window.repaint();
            state = 1;
        } else if(state == 1) {
            ball.stopAngle(true);
            ball.setVelocities();
            window.repaint();
            state = 2;
        } else if(state == 3){
            state = 0;
            ball.initializeScreen();
        }
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

