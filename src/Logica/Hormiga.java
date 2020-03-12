
package Logica;

import javax.swing.ImageIcon;


public class Hormiga extends Animal{

    // Atributos
    private int npasosd = 0;
    
    private final ImageIcon HORMIGA0 = new ImageIcon(getClass().getResource("/Icons/Hormiga0.png"));
    private final ImageIcon HORMIGA1 = new ImageIcon(getClass().getResource("/Icons/Hormiga1.png"));
    private final ImageIcon HORMIGA2 = new ImageIcon(getClass().getResource("/Icons/Hormiga2.png"));
    private final ImageIcon HORMIGA3 = new ImageIcon(getClass().getResource("/Icons/Hormiga3.png"));
    //Fin atributos
    
    //Constructores
    public Hormiga(int x, int y, int mirada) {
        super(x, y, mirada);
    }

    public Hormiga(int x, int y, int mirada, boolean visible) {
        super(x, y, mirada, visible);
    }
    //Fin constructores
    
    public void reproducirse(Celda[][] Organismos, Hormiga aux, int j, int i) {
        aux.visible = false;
        aux.mirada = (super.mirada+2)%4;
        try {
            switch(super.mirada){
                case 0:
                    if(Organismos[j][i+1] instanceof Planta ||
                            Organismos[j][i+1] instanceof CeldaVacia){
                        Organismos[j][i+1] = aux;
                    }      
                    break;
                case 1:
                    if(Organismos[j-1][i] instanceof Planta ||
                            Organismos[j-1][i] instanceof CeldaVacia){
                        Organismos[j-1][i] = aux;
                    }
                    break;
                case 2:
                    if(Organismos[j][i-1] instanceof Planta ||
                            Organismos[j][i-1] instanceof CeldaVacia){
                        Organismos[j][i-1] = aux;
                    }
                    break;
                default:
                    if(Organismos[j+1][i] instanceof Planta ||
                            Organismos[j][i+1] instanceof CeldaVacia){
                        Organismos[j+1][i] = aux;
                    }                      
            }
            Organismos[x][y] = Organismos[j][i];
            Organismos[j][i] = new Hormiga(j, i, (super.mirada + 3)%4, false);   
        } catch (ArrayIndexOutOfBoundsException e) {
            Organismos[x][y] = Organismos[j][i];
            Organismos[j][i] = aux;
            aux.voltearMirada(3);
        }
        
    }
    
    @Override
    public void mover(Celda[][] Organismos, int j, int i){
        if(super.visible && paso(j, i)){
            Celda aux = Organismos[super.x][super.y];

            if(aux instanceof Planta){ 
                this.voltearMirada(1);
                Organismos[super.x][super.y] = Organismos[j][i];
                Organismos[j][i] = new CeldaVacia();
                npasosd = 0;
            }else if(aux instanceof Oso){
                Organismos[j][i] = new CeldaVacia();
                ((Oso)aux).voltearMirada(1);
            }else if(aux instanceof Hormiga){
                reproducirse(Organismos, (Hormiga)aux, j, i);
            }else{
                if(npasosd<8){
                    this.voltearMirada(3);
                    npasosd++;
                } else{
                    this.voltearMirada(super.Azar.nextInt(2));
                    npasosd = 0;
                }
                Organismos[super.x][super.y]= Organismos[j][i];
                Organismos[j][i] = new CeldaVacia();
            } 
        }  
        super.visible = false;
    }
   
    @Override
    public ImageIcon icon() {
        super.visible = true;
        switch (super.mirada){
            case 0: return HORMIGA0;
            case 1: return HORMIGA1;
            case 2: return HORMIGA2;
            default: return HORMIGA3;
        }
    }
}
