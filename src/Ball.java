// Long Drive by Zander Deutch
import javax.swing.*;
import java.awt.*;

public class Ball {

    // Instance Variables

    private final Image ball;

    // Instance variables for bar
    private int barHeight;
    private boolean isBarUp;
    private boolean isBarStopped;

    // Instance variables for angle
    private int angle;
    private boolean isAngleStopped;
    private boolean isAngleUp;

    // Instance variables for distance
    private double x;
    private double y;
    private double yVelocity;
    private double xVelocity;
    private double xDistance;
    private int longestYards;

    // Constants
    public static final int TIME_INCREMENT = LongDriveFrontEnd.WINDOW_WIDTH / 400;
    public final static int STARTING_Y = LongDriveFrontEnd.WINDOW_HEIGHT - 105;
    public final static int STARTING_X = 30;
    public final static int BALLSTARTINGSIZE = LongDriveFrontEnd.WINDOW_WIDTH / 75;
    public final static int MULTIPLIER = LongDriveFrontEnd.WINDOW_WIDTH / 20;
    private Font normalFont;



    // Constructor
    public Ball() {
        // Create ball image
        ball = new ImageIcon("Resources/Ball.png").getImage();
        initializeScreen();
    }

    public void initializeScreen() {
        // Create and set new font
        normalFont = new Font("serif", Font.PLAIN, 25);
        // Initialize instance variables
        xDistance = 0.0;
        barHeight = LongDriveFrontEnd.WINDOW_HEIGHT / 8;
        angle = 270;
        isBarUp = true;
        isBarStopped = false;
        isAngleStopped = false;
        isAngleUp = true;
        x = STARTING_X * 3;
        y = STARTING_Y;
        yVelocity = 0;
        xVelocity = 0;
    }

    // Ball draw method for the initial screen
    public void draw(Graphics g) {

        // Draw the ball using x and y coordinates
        g.drawImage(ball, (int) Math.round(x), (int) Math.round(y), BALLSTARTINGSIZE, BALLSTARTINGSIZE, null);

        // Draw the bar and write out the description
        g.fillRect(LongDriveFrontEnd.WINDOW_WIDTH / 8, LongDriveFrontEnd.WINDOW_HEIGHT / 8, BALLSTARTINGSIZE, this.barHeight);
        g.setFont(normalFont);
        LongDriveFrontEnd.drawCenteredString(g, "Bar Power: " + getBarHeight() / 10, LongDriveFrontEnd.WINDOW_WIDTH / 8, LongDriveFrontEnd.WINDOW_HEIGHT / 9);

        // Make the font bigger
        strokeStyle(g);

        // Draw the angle and write out the description
        // Use trig to figure out the angle increment each time the bar gets updated
        g.drawLine(LongDriveFrontEnd.WINDOW_WIDTH / 4, LongDriveFrontEnd.WINDOW_HEIGHT / 3, (int) ((MULTIPLIER *
                Math.cos(Math.toRadians(angle))) + ((double) LongDriveFrontEnd.WINDOW_WIDTH / 4)), (int) ((MULTIPLIER * Math.sin(Math.toRadians(angle))) +
                ((double) LongDriveFrontEnd.WINDOW_HEIGHT / 3)));

        // Do 360 - angle to make up for disoriented window coordinate plane
        LongDriveFrontEnd.drawCenteredString(g, "Angle Power: " + (360 - getAngle()), LongDriveFrontEnd.WINDOW_WIDTH / 4, LongDriveFrontEnd.WINDOW_HEIGHT / 5);
    }

    // Draw method for when the user has picked their power and angle
    public void drawSecondScreen(Graphics g) {

        // update the ball position using x and y variables
        g.drawImage(ball, (int) Math.round(x), (int) Math.round(y), BALLSTARTINGSIZE / 2, BALLSTARTINGSIZE / 2, null);

        // Set size and style of font
        strokeStyle(g);
        g.setFont(normalFont);

        // Display the yards the user is hitting and the longest drive
        LongDriveFrontEnd.drawCenteredString(g, "Yards: " + getYards(), LongDriveFrontEnd.WINDOW_WIDTH / 2, LongDriveFrontEnd.WINDOW_HEIGHT / 9);
        LongDriveFrontEnd.drawCenteredString(g, "Longest Drive: " + getLongestYards(), LongDriveFrontEnd.WINDOW_WIDTH / 2, LongDriveFrontEnd.WINDOW_HEIGHT / 6);
    }

    // Method which sets the font to be a bigger size
    public void strokeStyle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(TIME_INCREMENT * 4));
    }

    // Method which determines the updated ball position
    public void propagateBall() {

        // X simply updates based on how far forward it goes
        x += xVelocity;

        // Y velocity starts negative so you need to compensate
        yVelocity += 0.095;
        y += yVelocity;

        // Update the x distance to how far it went based on new and old position
        xDistance = x - (STARTING_X * 3);

        // If this amount forward is greater than the furthest distance
        if(getYards() > longestYards) {
            // Update the amount of yards
            longestYards = getYards();
        }
    }

    // This method sets the velocities of x and y
    public void setVelocities() {
        System.out.println("yVelocity " + yVelocity);
        // Using some trig and then diving to make sure that the ball doesn't go too far
        yVelocity = ((barHeight * Math.sin(Math.toRadians(angle))) / 30);
        xVelocity = (barHeight * Math.cos(Math.toRadians(angle))) / 30;
    }

    // Determines whether the bar should be moving up or down
    public void determineBarIsUp() {
        // If the bar exceeds a certain height
        if (barHeight >= LongDriveFrontEnd.WINDOW_WIDTH / 4) {
            // It will start retracting
            isBarUp = false;
            // Otherwise it keeps moving down
        } else if (barHeight <= 0) {
            isBarUp = true;
        }
    }

    // Determine whether the angle should move up or down
    public void determineAngleIsUp() {
        // If angle is in the first quadrant
        if (angle >= 360) {
            // Make it move down
            isAngleUp = false;
            // If angle is in the second quadrant
        } else if (angle <= 270) {
            // Make it move up
            isAngleUp = true;
        }
    }

    // Getters for instance variables
    public int getBarHeight() {
        return barHeight;
    }

    public boolean getIsUp() {
        return isBarUp;
    }

    public boolean getIsBarStopped() {
        return isBarStopped;
    }

    public int getAngle() {
        return angle;
    }

    public double getY() {
        return y;
    }

    public int getYards() {
        return (int) xDistance / 3;
    }

    public int getLongestYards() {
        return longestYards;
    }


    // Setters for instance variables
    public void setBarHeight(int barHeight) {
        this.barHeight = barHeight;
    }

    public void setIsBarStopped(boolean stopped) {
        isBarStopped = stopped;

    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void stopAngle(boolean stop) {
        isAngleStopped = stop;
    }

    public boolean isAngleStopped() {
        return isAngleStopped;
    }

    public boolean isAngleUp() {
        return isAngleUp;
    }



}
