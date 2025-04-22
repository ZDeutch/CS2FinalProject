import javax.swing.*;
import java.awt.*;

public class LongDriveFrontEnd extends JFrame {
    public final static int WINDOW_WIDTH = 800;
    public final static int WINDOW_HEIGHT = 800;
    public final static Color BACKGROUND = new Color(255, 255, 255);
    private Ball ball;
    private LongDrive game;
    private Image background;
    private Image backSwing;
    private Image throughSwing;

    public LongDriveFrontEnd(LongDrive game, Ball b) {
        game = this.game;
        ball = b;
        background = new ImageIcon("Resources/LongDriveBackGround.png").getImage();
        backSwing = new ImageIcon("Resources/BackSwing.png").getImage();
        throughSwing = new ImageIcon("Resources/ThroughSwing.png").getImage();


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Long Drive Contest");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawImage(backSwing,75,WINDOW_HEIGHT - 275,200, 200, this);
        g.setColor(Color.BLACK);
        ball.draw(g);
    }
}
