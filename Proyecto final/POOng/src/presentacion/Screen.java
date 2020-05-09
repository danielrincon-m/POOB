package presentacion;

import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JPanel {

    public Screen() {
        setPreferredSize(new Dimension(Application.WIDTH, Application.HEIGHT));
        prepareElements();

    }

    protected abstract void prepareElements();
}
