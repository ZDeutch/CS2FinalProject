import javax.swing.*;
import java.awt.*;

public class LongDriveFrontEnd extends JFrame {
    public final static int WINDOW_WIDTH = 800;
    public final static int WINDOW_HEIGHT = 800;
    public final static Color BACKGROUND = new Color(255, 255, 255);
    private Ball ball;
    private LongDrive game;
    private Image firstBackground;
    private Image secondBackgroound;
    private Image backSwing;
    private Image throughSwing;

    public LongDriveFrontEnd(LongDrive game, Ball b) {
        this.game = game;
        ball = b;
        firstBackground = new ImageIcon("Resources/LongDriveBackGround.png").getImage();
        secondBackgroound = new ImageIcon("Resources/ZoomedOutBackground.png").getImage();
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
        g.drawImage(backSwing,75,WINDOW_HEIGHT - 275,WINDOW_WIDTH / 4, 200, this);
        g.setColor(Color.BLACK);
        ball.draw(g);
    }
    public void gameScreen2(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.drawImage(secondBackgroound, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawImage(throughSwing,75,WINDOW_HEIGHT - 275,100, 100, this);
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
