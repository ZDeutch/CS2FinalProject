import javax.swing.*;
import java.awt.*;

public class LongDriveFrontEnd extends JFrame {
    public final static int WINDOW_WIDTH = 1500;
    public final static int WINDOW_HEIGHT = 900;
    public final static Color BACKGROUND = new Color(255, 255, 255);
    private final Ball ball;
    private final LongDrive game;
    private final Image firstBackground;
    private final Image secondBackground;
    private final Image backSwing;
    private final Image throughSwing;

    public LongDriveFrontEnd(LongDrive game, Ball ball) {
        this.game = game;
        this.ball = ball;
        firstBackground = new ImageIcon("Resources/LongDriveBackGround.png").getImage();
        secondBackground = new ImageIcon("Resources/ZoomedOutBackground.png").getImage();
        backSwing = new ImageIcon("Resources/BackSwing.png").getImage();
        throughSwing = new ImageIcon("Resources/ThroughSwing.png").getImage();


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Long Drive Contest");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void gameScreen1(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.drawImage(firstBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawImage(backSwing,Ball.STARTING_X,WINDOW_HEIGHT - 275,WINDOW_WIDTH / 4, WINDOW_HEIGHT / 4, this);
        g.setColor(Color.BLACK);
        ball.draw(g);
    }
    public void gameScreen2(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.drawImage(secondBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawImage(throughSwing,Ball.STARTING_X,WINDOW_HEIGHT - 150,WINDOW_WIDTH / 8, WINDOW_HEIGHT / 8, this);
        g.setColor(Color.BLACK);
        ball.drawSecondScreen(g);
    }

    public void paint(Graphics g) {
        if(game.isZoomedInScreen()) {
            gameScreen1(g);
        } else {
            gameScreen2(g);
        }
    }
}
