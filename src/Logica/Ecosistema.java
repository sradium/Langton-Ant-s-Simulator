
package Logica;

import java.util.Random;
import javax.swing.ImageIcon;

public class Ecosistema {   
    
    //Atributos de la clase que permites gestionar todos los objetos
    private Celda[][] Organismos = new Celda[50][50];
    Random Azar = new Random();
    private final int nPlantas = 1000;
    private final int nHormigas = 3;
    private final int nOsos = 4;
    //Fin atributos
    
    //Constructor
    public Ecosistema(){
        this.poblar();
    }
    //Fin constructor
    
    /*
    * Crea todos los objetos iniciales y completa los espacios vacíos con 
    * CeldasVacias para eliminar el java.lang.NullPointerException
    */
    public void poblar(){
        
        for(int i = 0; i < nHormigas; i++){
            int x = Azar.nextInt(50);
            int y = Azar.nextInt(50);
            int d = Azar.nextInt(4)%4;
            if(Organismos[x][y] == null){
                Organismos[x][y] = new Hormiga(x,y,d);
            }
            else{
                i--;
            }
        }
        
        int S = nOsos/2;
        
        for(int i = 0; i < nOsos; i++){
            int x = Azar.nextInt(50);
            int y = Azar.nextInt(50);
            int d = Azar.nextInt(4);
            if(Organismos[x][y] == null && i<S){
                Organismos[x][y] = new Oso(x, y, d, 'M');
            }
            else if(Organismos[x][y] == null){
                Organismos[x][y] = new Oso(x, y, d, 'F');
            }            
            else{
                i--;
            }
        }
        
        for(int i = 0; i < nPlantas; i++){
            int x = Azar.nextInt(50);
            int y = Azar.nextInt(50);
            if(Organismos[x][y] == null){
                Organismos[x][y] = new Planta(x,y);
            }
            else{
                i--;
            }
        }
        
        for(int j = 0; j < 50; j++){
            for(int i = 0; i<50; i++){
                if(Organismos[j][i] == null){
                    Organismos[j][i] = new CeldaVacia();
                }
            }
        }
    }
    //Fin procedimiento
    
    /*
    * Mueve todos los animales, si se desea agregar otro animal a parte de la 
    * hormiga o el oso hormiguero basta con que herede de la clase Animal para 
    * que se pueda mover por la matriz
    */
    public void movimiento(){
        for(int j=0; j< Organismos.length ; j++){
            for(int i=0; i < Organismos.length; i++){
                if(Organismos[j][i] instanceof Animal){
                    ((Animal)(Organismos[j][i])).mover(Organismos, j, i);
                }
            }
        }
    }
    //Fin procedimiento

    // Reproduce todas las plantas que sean celdas visibles en la matriz
    public void fotosintesis(){
        for(int j=0; j< Organismos.length ; j++){
            for(int i=0; i < Organismos.length; i++){
                if(Organismos[j][i] instanceof Planta){
                    ((Planta)Organismos[j][i]).reproducirse(Organismos, j, i);
                }
            }
        }
    }
    //Fin procedimiento
    
    // Reotrna la imagen de un objeto de la matriz
    public ImageIcon color(int j, int i){
        return Organismos[j][i].icon();
    }
    //Fin función
}
