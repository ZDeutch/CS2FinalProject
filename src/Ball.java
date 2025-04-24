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

    public Ball() {
        barHeight = 100;
        angle = 270;
        isBarUp = true;
        isBarStopped = false;
        isAngleStopped = false;
        isAngleUp = true;
        ball = new ImageIcon("Resources/Ball.png").getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(ball, 225, LongDriveFrontEnd.WINDOW_HEIGHT - 110, 25, 25, window);
        g.fillRect(100, 100, 20, this.barHeight);
        strokeStyle(g);
        g.drawLine(200,250, (int) ((40 * Math.cos(Math.toRadians(angle))) + 200), (int) ((40 * Math.sin(Math.toRadians(angle))) + 250));
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
        if(barHeight >= 200) {
            isBarUp = false;
        } else if(barHeight <= 0) {
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
        if(angle >= 360) {
            isAngleUp = false;
        } else if(angle <= 270) {
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
}

