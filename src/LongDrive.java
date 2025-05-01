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
    private boolean zoomedInScreen;

    public LongDrive() {
        state = 0;
        ball = new Ball();
        window = new LongDriveFrontEnd(this, ball);
        window.addKeyListener(this);
        zoomedInScreen = true;
    }

    public void actionPerformed(ActionEvent e) {
        if (!isZoomedInScreen()) {
            if ((int) (ball.getY()) <= Ball.STARTING_Y) {
                ball.propagateBall();
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
            } else {
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
        return zoomedInScreen;
    }

    public void setZoomedInScreen(boolean zoomedInScreen) {
        this.zoomedInScreen = zoomedInScreen;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (state == 0) {
            ball.setIsBarStopped(true);
            window.repaint();
            state = 1;
        } else {
            ball.stopAngle(true);
            ball.setVelocities();
            window.repaint();
            zoomedInScreen = false;
        }

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

