
package Logica;

import java.util.Random;
import javax.swing.ImageIcon;

public abstract class Celda {
    //Atrubutos
    protected int x, y;
    protected boolean visible;
    protected Random Azar = new Random();
    //Fin atributos
    
    //Constructores
    public Celda(){
        
    }
    
    public Celda(int x, int y){
        this.x=x;
        this.y=y;
        visible = true;
    }

    public Celda(int x, int y, boolean visible){
        this.x=x;
        this.y=y;
        this.visible = visible;
    }
    //Fin constructores
    
    //Función que retorna la imagen de la celda
    public abstract ImageIcon icon();
}
