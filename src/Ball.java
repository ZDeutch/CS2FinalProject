import javax.swing.*;
import java.awt.*;

public class Ball {
    private boolean isBarUp;
    private boolean isBarStopped;
    private final Image ball;
    private int barHeight;
    private int angle;
    private boolean isAngleStopped;
    private boolean isAngleUp;
    private double x;
    private double y;
    public static final int TIME_INCREMENT = LongDriveFrontEnd.WINDOW_WIDTH / 400;
    public static final int EARTH_GRAVITY = LongDriveFrontEnd.WINDOW_WIDTH / 80;
    private double yVelocity;
    private double xVelocity;
    public final static int STARTING_Y = LongDriveFrontEnd.WINDOW_HEIGHT - 105;
    public final static int STARTING_X = 30;
    public final static int BALLSTARTINGSIZE = LongDriveFrontEnd.WINDOW_WIDTH / 75;
    public final static int MULTIPLIER = LongDriveFrontEnd.WINDOW_WIDTH / 20;
    private double yards;
    private int longestYards;

    public Ball() {
        longestYards = 0;
        yards = 0.0;
        barHeight = LongDriveFrontEnd.WINDOW_HEIGHT / 8;
        angle = 270;
        isBarUp = true;
        isBarStopped = false;
        isAngleStopped = false;
        isAngleUp = true;
        ball = new ImageIcon("Resources/Ball.png").getImage();
        x = STARTING_X * 3;
        y = STARTING_Y;
        yVelocity = 0;
        xVelocity = 0;
    }

    public void draw(Graphics g) {
        g.drawImage(ball, (int) Math.round(x), (int) Math.round(y), BALLSTARTINGSIZE, BALLSTARTINGSIZE, null);
        g.fillRect(LongDriveFrontEnd.WINDOW_WIDTH / 8, LongDriveFrontEnd.WINDOW_HEIGHT / 8, BALLSTARTINGSIZE, this.barHeight);
        strokeStyle(g);
        g.drawLine(LongDriveFrontEnd.WINDOW_WIDTH / 4, LongDriveFrontEnd.WINDOW_HEIGHT / 3, (int) ((MULTIPLIER *
                Math.cos(Math.toRadians(angle))) + ((double) LongDriveFrontEnd.WINDOW_WIDTH / 4)), (int) ((MULTIPLIER * Math.sin(Math.toRadians(angle))) +
                ((double) LongDriveFrontEnd.WINDOW_HEIGHT / 3)));
    }

    public void drawSecondScreen(Graphics g) {
        g.drawImage(ball, (int) Math.round(x), (int) Math.round(y), BALLSTARTINGSIZE / 2, BALLSTARTINGSIZE / 2, null);
        strokeStyle(g);
        g.drawString("Yards: " + getYards() / 3, 100,100);
        longestYards = getYards();
        g.drawString("Longest Drive: " + getLongestYards(), 100,150);
    }

    public void strokeStyle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(TIME_INCREMENT * 4));
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
        if (barHeight >= LongDriveFrontEnd.WINDOW_WIDTH / 4) {
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
        x += xVelocity;
        yVelocity += 0.095;
        y += yVelocity;
        yards = x - (STARTING_X * 3);
    }

    public void setVelocities() {
        System.out.println("yVelocity " + yVelocity);
        yVelocity = ((barHeight * Math.sin(Math.toRadians(angle))) / 30);
        xVelocity = (barHeight * Math.cos(Math.toRadians(angle))) / 30;
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

    public double getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }


    public int getYards() {
        return (int) yards;
    }

    public int getLongestYards() {
        return longestYards;
    }
}
