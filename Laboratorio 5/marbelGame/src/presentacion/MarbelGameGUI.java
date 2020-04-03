package presentacion;

import javax.swing.*;
import java.awt.*;

public class MarbelGameGUI extends JFrame {
    private MarbelGameGUI() {
        prepareElementos();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(dimension.width/2, dimension.height/2));
    }

    private void prepareElementos() {
        setTitle("Marbel Game");
    }

    public static void main(String[] args) {
        MarbelGameGUI gui = new MarbelGameGUI();
        gui.setVisible(true);
    }
}

