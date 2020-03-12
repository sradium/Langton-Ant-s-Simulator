
package Logica;

import javax.swing.ImageIcon;


public class Planta extends Celda implements SerVivo{
    
     private final ImageIcon PLANTA = new ImageIcon(getClass().getResource("/Icons/Planta.png"));

    public Planta(int x, int y) {
        super(x, y);
    }
    
    public Planta(int x, int y, boolean visible) {
        super(x, y, visible);
    }
    
    //Crea nuevas plantas adyacente a ella
    public void reproducirse(Celda[][] Organismos, int j, int i) {
        if(visible){    
            try {
                if(Organismos[j][i-1] instanceof CeldaVacia && 
                        Organismos[j][i+1] instanceof CeldaVacia &&
                            Organismos[j-1][i] instanceof CeldaVacia &&  
                                Organismos[j+1][i] instanceof CeldaVacia){
                    Organismos[j][i-1] = new Planta(j, i-1, false);
                    Organismos[j][i+1] = new Planta(j, i+1, false);
                    Organismos[j-1][i] = new Planta(j-1, i, false);
                    Organismos[j+1][i] = new Planta(j+1, i, false);
                }
                
            } catch (ArrayIndexOutOfBoundsException e) {
                try {
                    if(Organismos[j][i+1] instanceof CeldaVacia &&
                            Organismos[j-1][i] instanceof CeldaVacia &&  
                                Organismos[j+1][i] instanceof CeldaVacia){
                        Organismos[j][i+1] = new Planta(j, i+1, false);
                        Organismos[j-1][i] = new Planta(j-1, i, false);
                        Organismos[j+1][i] = new Planta(j+1, i, false);
                    }

                } catch (ArrayIndexOutOfBoundsException f) {
                    try {
                        if(Organismos[j][i-1] instanceof CeldaVacia && 
                                Organismos[j-1][i] instanceof CeldaVacia &&  
                                    Organismos[j+1][i] instanceof CeldaVacia){
                            Organismos[j][i-1] = new Planta(j, i-1, false);
                            Organismos[j-1][i] = new Planta(j-1, i, false);
                            Organismos[j+1][i] = new Planta(j+1, i, false);
                        }

                    } catch (ArrayIndexOutOfBoundsException g) {
                        try {
                            if(Organismos[j][i-1] instanceof CeldaVacia && 
                                    Organismos[j][i+1] instanceof CeldaVacia && 
                                            Organismos[j+1][i] instanceof CeldaVacia){
                                Organismos[j][i-1] = new Planta(j, i-1, false);
                                Organismos[j][i+1] = new Planta(j, i+1, false);
                                Organismos[j+1][i] = new Planta(j+1, i, false);
                            }

                        } catch (ArrayIndexOutOfBoundsException h) {
                            try {
                                if(Organismos[j][i-1] instanceof CeldaVacia && 
                                        Organismos[j][i+1] instanceof CeldaVacia &&
                                            Organismos[j-1][i] instanceof CeldaVacia){
                                    Organismos[j][i-1] = new Planta(j, i-1, false);
                                    Organismos[j][i+1] = new Planta(j, i+1, false);
                                    Organismos[j-1][i] = new Planta(j-1, i, false);
                                }

                            } catch (ArrayIndexOutOfBoundsException d) {}
                        }
                    }
                }
            }
            visible=false;
        }else{
            visible = true;
        }
    }
    //Fin procedimiento

    @Override
    public ImageIcon icon() {
        return PLANTA;
    }
}
