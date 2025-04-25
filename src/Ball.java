import javax.swing.*;
import java.awt.*;

public class Ball {
    private boolean isBarUp;
    private boolean isBarStopped;
    private Image ball;
    private LongDriveFrontEnd window;
    private int barHeight;
    private int angle;
    private boolean isAngleStopped;
    private boolean isAngleUp;
    private double x;
    private double y;
    public static final int TIME_INCREMENT = 2;
    public static final int EARTH_GRAVITY = 10;
    private double yVelocity;

    public Ball() {
        barHeight = 100;
        angle = 270;
        isBarUp = true;
        isBarStopped = false;
        isAngleStopped = false;
        isAngleUp = true;
        ball = new ImageIcon("Resources/Ball.png").getImage();
        x = 225;
        y = LongDriveFrontEnd.WINDOW_HEIGHT - 110;
        yVelocity = barHeight;
    }

    public void draw(Graphics g) {
        g.drawImage(ball, (int) Math.round(x), (int) Math.round(y), 25, 25, window);
        g.fillRect(100, 100, 20, this.barHeight);
        strokeStyle(g);
        g.drawLine(200, 250, (int) ((40 * Math.cos(Math.toRadians(angle))) + 200), (int) ((40 * Math.sin(Math.toRadians(angle))) + 250));
    }

    public void drawSecondScreen(Graphics g) {
        g.drawImage(ball, (int) Math.round(x), (int) Math.round(y), 25, 25, window);
    }

    public void strokeStyle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(7));
    }

    public int getBarHeight() {
        return barHeight;
    }

    public void setBarHeight(int barHeight) {
        this.barHeight = barHeight;
    }

    public boolean getIsUp() {
        return isBarUp;
    }

    public void determineBarIsUp() {
        if (barHeight >= 200) {
            isBarUp = false;
        } else if (barHeight <= 0) {
            isBarUp = true;
        }
    }

    public boolean getIsBarStopped() {
        return isBarStopped;
    }

    public void setIsBarStopped(boolean stopped) {
        isBarStopped = stopped;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void determineAngleIsUp() {
        if (angle >= 360) {
            isAngleUp = false;
        } else if (angle <= 270) {
            isAngleUp = true;
        }
    }

    public void stopAngle(boolean stop) {
        isAngleStopped = stop;
    }

    public boolean isAngleStopped() {
        return isAngleStopped;
    }

    public void setAngleStopped(boolean angleStopped) {
        isAngleStopped = angleStopped;
    }

    public boolean isAngleUp() {
        return isAngleUp;
    }

    public void setAngleUp(boolean angleUp) {
        isAngleUp = angleUp;
    }

    public void propagateBall() {
        x =  x + barHeight * Math.cos(Math.toRadians(angle)) * TIME_INCREMENT;
        yVelocity -= (EARTH_GRAVITY * TIME_INCREMENT);
        y = y + (yVelocity * TIME_INCREMENT);
    }


    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }
}
