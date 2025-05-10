// Long Drive by Zander Deutch
import javax.swing.*;
import java.awt.*;

public class LongDriveFrontEnd extends JFrame {
    // Instance Variables

    // Adjustable window size set to the size of my computer screen
    public final static int WINDOW_WIDTH = 1500;
    public final static int WINDOW_HEIGHT = 900;
    public final static Color BACKGROUND = new Color(255, 255, 255);

    // Class objects to access methods and run the game logic
    private final Ball ball;

    // Imported Images for the background and golfer swings
    private final Image firstBackground;
    private final Image secondBackground;
    private final Image backSwing;
    private final Image throughSwing;

    // Constructor
    public LongDriveFrontEnd(Ball ball) {

        // Initializes class objects
        this.ball = ball;

        // Initializes images
        firstBackground = new ImageIcon("Resources/LongDriveBackGround.png").getImage();
        secondBackground = new ImageIcon("Resources/ZoomedOutBackground.png").getImage();
        backSwing = new ImageIcon("Resources/BackSwing.png").getImage();
        throughSwing = new ImageIcon("Resources/ThroughSwing.png").getImage();

        // Makes the Screen
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Long Drive Contest");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    // First Screen paint used to get the power and angle at a zoomed in level
    public void gameScreen1(Graphics g) {

        // Set the zoomed in background and back swing
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.drawImage(firstBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawImage(backSwing, Ball.STARTING_X, WINDOW_HEIGHT - 125, WINDOW_WIDTH / 20, WINDOW_HEIGHT / 20, this);
        g.setColor(Color.BLACK);

        // Call on ball draw method
        ball.draw(g);
    }

    // Second Screen paint to show the ball moving at a zoomed out level
    public void gameScreen2(Graphics g, boolean gameIsOver) {

        // Set the zoomed out background and through swing
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.drawImage(secondBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawImage(throughSwing, Ball.STARTING_X, WINDOW_HEIGHT - 125, WINDOW_WIDTH / 20, WINDOW_HEIGHT / 20, this);
        g.setColor(Color.BLACK);

        // Call on the second ball draw method
        ball.drawSecondScreen(g);

        // But if the game is over meaning the ball has stopped moving
        // Then call for the user to click again
        if(gameIsOver) {
            g.setColor(Color.BLACK);
            drawCenteredString(g, "Click Any Key to Play Again", WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);
        }
    }

    // Static method to draw strings so it appears centered
    public static void drawCenteredString(Graphics g, String s, int x, int y) {

        // Make the font black for prominence
        g.setColor(Color.BLACK);
        Font f = g.getFont();

        // Get half of the string length using Font methods
        int halfStringLength = g.getFontMetrics(f).stringWidth(s) / 2;

        // Draw string on center of screen minus half the string length
        g.drawString(s,x - halfStringLength, y);
    }

    // Paint method
    public void paint(Graphics g) {
        // If the state is 0, 1, or 2
        if (LongDrive.getState() == 0 || LongDrive.getState() == 1) {
            // Call on the first game screen
            gameScreen1(g);
        } else {
            // Otherwise go to the second game screen
            gameScreen2(g, LongDrive.getState() == 3);
        }
    }
}
