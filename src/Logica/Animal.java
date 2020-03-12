
package Logica;

public abstract class Animal extends Celda implements SerVivo {
    
    protected int mirada;

    public Animal(int x, int y, int mirada) {
        super(x, y);
        this.mirada = mirada;
    }

    public Animal(int x, int y, int mirada, boolean visible) {
        super(x, y, visible);
        this.mirada = mirada;
    }
    
    /*
    * Voltea la mirada del animal, si el valor de SentidoGiro es 1 Voltea a la 
    * derecha, si es 3 a la izquierda, si es 2 da un giro de 180º y si es 0 no
    * gira
    */
    public void voltearMirada(int SentidoGiro) {

            mirada = (mirada + SentidoGiro)%4;        
    }
    //Fin procedimiento
    
    /*
    * Procedimiento que debe implementar todo animal para su movimiento, para su 
    * correcto funcionamiento en alguna parte se debe llamar la función paso
    * para así evitar que el animal se salga de la matriz
    */
    public abstract void mover(Celda[][] Organismos, int j, int i);
    
    /*
    * Función que cambia las coordenadas donde se encuentra el animal y valida si
    * está por salirse de la matriz. Cuando la función retorne true significa que
    * se avanzo un paso sin problemas, si es false representa que se encontró con
    * un borde y solo pudo voltear la mirada sin realizar ningún paso
    */
    public boolean paso(int j, int i) {
        if((j == 0 && mirada == 3)||(j == 49 && mirada == 1)||(i == 0 && mirada == 0)||(i == 49 && mirada == 2)){ //Verifica si está en el borde
            this.voltearMirada(1+(Azar.nextInt(2) * 2));
            return false;
        } else {
            switch (mirada){
                case 0:
                    y--;
                    break;
                case 1:
                    x++;
                    break;
                case 2:
                    y++;
                    break;
                default:
                    x--;
                    break;
            }
            return true;
        }
    }
    //Fin función
}
