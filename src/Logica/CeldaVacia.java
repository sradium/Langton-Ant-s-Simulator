
package Logica;

import javax.swing.ImageIcon;


public class CeldaVacia extends Celda{
    
    private final ImageIcon TIERRA = new ImageIcon(getClass().getResource("/Icons/Tierra.png"));

    @Override
    public ImageIcon icon() {
        return TIERRA;
    }
    
}
