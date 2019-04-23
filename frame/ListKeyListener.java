package frame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class ListKeyListener implements KeyListener {
    private KeyListener keyListener;

    public ListKeyListener(KeyListener keyListener){
        this.keyListener = keyListener;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
