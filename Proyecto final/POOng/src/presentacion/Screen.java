package presentacion;

import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JPanel {

    protected Application application;

    public Screen(Application application) {
        setPreferredSize(new Dimension(Application.WIDTH, Application.HEIGHT));
        this.application = application;
        prepareElements();
        prepareAccionesElemento();
    }

    protected abstract void prepareElements();

    protected abstract void prepareAccionesElemento();
}
