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
        angle = 50;
        isBarUp = true;
        isBarStopped = false;
        isAngleStopped = false;
        isAngleUp = true;
        ball = new ImageIcon("Resources/Ball.png").getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(ball, 225, LongDriveFrontEnd.WINDOW_HEIGHT - 110, 25, 25, window);
        g.fillRect(100, 100, 20, this.barHeight);
        g.drawLine(200,300 - angle,300,300);
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
        if(angle >= 90) {
            isAngleUp = false;
        } else if(barHeight <= 0) {
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

