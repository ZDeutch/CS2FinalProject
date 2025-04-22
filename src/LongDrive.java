import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LongDrive implements ActionListener, KeyListener {
    private LongDriveFrontEnd window;
    private Ball ball;
    private static final int METER_SPEED = 1;
    private static int state;

    public LongDrive() {
        state = 0;
        ball = new Ball();
        window = new LongDriveFrontEnd(this, ball);
        window.addKeyListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(state == 0) {
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
        window.repaint();
    }

    public void barIncrement() {
        ball.setBarHeight(ball.getBarHeight() + 1);
    }

    public void barDecrement() {
        ball.setBarHeight(ball.getBarHeight() - 1);
    }

    public void angleIncrement() { ball.setAngle(ball.getAngle() + 1); }

    public void angleDecrement() {
        ball.setAngle(ball.getAngle() - 1);
    }

    public static void main(String[] args) {
        LongDrive game = new LongDrive();
        Timer clock = new Timer(METER_SPEED, game);
        clock.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(state == 0) {
            ball.setIsBarStopped(true);
            window.repaint();
            state = 1;
        } else {
            ball.stopAngle(true);
            window.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

