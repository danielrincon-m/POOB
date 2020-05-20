package aplicacion.game.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/*
TODO: Implementar que el input sea procesado cada frame,
 los llamados de tipo: "Se comenzó a presionar la tecla en este frame" no funcionan,
 ya que el sistema de entrada no está sincronizado con el gameloop
*/

public final class Input implements KeyListener {
    //one for each ascii character.
    private boolean[] key_state_down = new boolean[256]; //true if not pressed
    //variable that indicates when any key(s) are being pressed.
    private boolean keyPressed = false;
    //variable that indicates that some key was released this frame.
    private boolean keyReleased = false; //cleared every frame.
    //the only instantiated object
    private static Input instance = new Input();

    private ArrayList<InputListener> listeners = new ArrayList<>();

    /**
     * Empty Constructor: nothing really needed here.
     */
    protected Input() {
    }

    /**
     * Singleton accessor.
     *
     * @return One and only InputManager object.
     */
    public static Input getInstance() {
        return instance;
    }


    /**
     * Agrega un nuevo listener para ser llamado
     * @param listener el listener a agregar
     */
    public void addInputListener(InputListener listener) {
        listeners.add(listener);
    }

    public void removeInputListener(InputListener listener) {
        listeners.remove(listener);
    }

    /**
     * This function is specified in the KeyListener interface. It sets the
     * state elements for whatever key was pressed.
     *
     * @param e The KeyEvent fired by the awt Toolkit
     */
    public void keyPressed(KeyEvent e) {
        //System.out.println("InputManager: A key has been pressed code=" + e.getKeyCode());
        if (e.getKeyCode() >= 0 && e.getKeyCode() < 256) {
            key_state_down[e.getKeyCode()] = true;
            keyPressed = true;
            keyReleased = false;
            for (InputListener listener : listeners) {
                listener.onKeyPressed(e);
            }
        }
    }

    /**
     * This function is specified in the KeyListener interface. It sets the
     * state elements for whatever key was released.
     *
     * @param e The KeyEvent fired by the awt Toolkit.
     */
    public void keyReleased(KeyEvent e) {
        //System.out.println("InputManager: A key has been released code=" + e.getKeyCode());
        if (e.getKeyCode() >= 0 && e.getKeyCode() < 256) {
            key_state_down[e.getKeyCode()] = false;
            keyPressed = false;
            keyReleased = true;
            for (InputListener listener : listeners) {
                listener.onKeyReleased(e);
            }
        }
    }

    /**
     * This function is called if certain keys are pressed namely those used
     * for text input.
     *
     * @param e The KeyEvent fired by the awt Toolkit.
     */
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Returns true if the key (0-256) is being pressed use the KeyEvent.VK_
     * key variables to check specific keys.
     *
     * @param key The ascii value of the keyboard key being checked
     * @return true is that key is currently being pressed.
     */
    public boolean isKeyDown(int key) {
        return key_state_down[key];
    }

    /**
     * Returns true if the key (0-256) is being pressed use the KeyEvent.VK_
     * key variables to check specific keys.
     *
     * @param key The ascii value of the keyboard key being checked
     * @return true is that key is currently being pressed.
     */
    public boolean isKeyUp(int key) {
        return !key_state_down[key];
    }

    /**
     * In case you want to know if a user is pressing a key but don't care which
     * one.
     *
     * @return true if one or more keys are currently being pressed.
     */
    public boolean isAnyKeyDown() {
        return keyPressed;
    }

    /**
     * In case you want to know if a user released a key but don't care which
     * one.
     *
     * @return true if one or more keys have been released this frame.
     */
    public boolean isAnyKeyUp() {
        return keyReleased;
    }

    /**
     * Only resets the key state up because you don't want keys to be showing
     * as up forever which is what will happen unless the array is cleared.
     */
    public void update() {
        //clear out the key up states
        keyReleased = false;
    }

} // InputManager
