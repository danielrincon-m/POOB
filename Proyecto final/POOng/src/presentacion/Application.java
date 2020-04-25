package presentacion;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    public static int WIDTH;
    public static int HEIGHT;

    public Application() {
        initFrame();
    }

    private void initFrame() {
        WIDTH = 1280;
        HEIGHT = 720;

        setSize(WIDTH, HEIGHT);
        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new CardLayout(10, 10));

        add(new StartScreen());

        pack();
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.setVisible(true);
    }
}
