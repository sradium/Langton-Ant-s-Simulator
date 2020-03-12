
package Logica;

import javax.swing.ImageIcon;

public class Oso extends Animal{
    
    //Atributos
    private int nHijos;
    private char sexo;
    private boolean sobrep = false;
    private int npasos = 0; 
    
    private final ImageIcon OSOM0 = new ImageIcon(getClass().getResource("/Icons/OsoM0.png"));
    private final ImageIcon OSOM1 = new ImageIcon(getClass().getResource("/Icons/OsoM1.png"));
    private final ImageIcon OSOM2 = new ImageIcon(getClass().getResource("/Icons/OsoM2.png"));
    private final ImageIcon OSOM3 = new ImageIcon(getClass().getResource("/Icons/OsoM3.png"));
      
    private final ImageIcon OSOF0 = new ImageIcon(getClass().getResource("/Icons/OsoF0.png"));
    private final ImageIcon OSOF1 = new ImageIcon(getClass().getResource("/Icons/OsoF1.png"));
    private final ImageIcon OSOF2 = new ImageIcon(getClass().getResource("/Icons/OsoF2.png"));
    private final ImageIcon OSOF3 = new ImageIcon(getClass().getResource("/Icons/OsoF3.png"));
    //Fin atributos
    
    //Constructores
    public Oso(int x, int y, int mirada, char sexo) {
        super(x, y, mirada);
        this.sexo = sexo;
        nHijos = 0;
    }
    
    public Oso(int x, int y, int mirada, char sexo, boolean visible) {
        super(x, y, mirada, visible);
        this.sexo = sexo;
        nHijos = 0;
    }
    //Fin constructores
    
    public void reproducirse(Celda[][] Organismos, Oso aux, int j, int i) {
        Organismos[super.x][super.y] = Organismos[j][i];
        aux.visible = false;
        aux.mirada = (super.mirada+2)%4;
        try {
            switch(super.mirada){
                case 0:
                    if(Organismos[j][i+1] instanceof Planta || 
                            Organismos[j][i+1] instanceof Hormiga || Organismos[j][i+1] instanceof CeldaVacia){
                        Organismos[j][i+1] = aux;
                    }  
                    break;
                case 1:
                    if(Organismos[j-1][i] instanceof Planta|| 
                            Organismos[j-1][i] instanceof Hormiga || Organismos[j-1][i] instanceof CeldaVacia){
                        Organismos[j-1][i] = aux;
                    }
                    break;
                case 2:
                    if(Organismos[j][i-1] instanceof Planta || 
                            Organismos[j][i-1] instanceof Hormiga || Organismos[j][i-1] instanceof CeldaVacia){
                        Organismos[j][i-1] = aux;
                    }
                    break;
                default:
                    if(Organismos[j+1][i] instanceof Planta || 
                            Organismos[j+1][i] instanceof Hormiga || Organismos[j+1][i] instanceof CeldaVacia){
                        Organismos[j+1][i] = aux;
                    }                
            }
            if(super.Azar.nextDouble() > 0.5){
                Organismos[j][i] = new Oso(j, i, (super.mirada + 3)%4,'M', false);
            } else{
                Organismos[j][i] = new Oso(j, i, (super.mirada + 3)%4,'F', false);
            }
            aux.nHijos ++;
            this.nHijos++;
        } catch (ArrayIndexOutOfBoundsException e) {
            Organismos[x][y] = Organismos[j][i];
            Organismos[j][i] = aux;
            aux.voltearMirada(3);
        }
            
        
    }
   
    @Override
    public void mover(Celda[][] Organismos, int j, int i) {
        if(super.visible && paso(j, i)){
            Celda aux = Organismos[super.x][super.y];
            if(aux instanceof Hormiga){
                this.voltearMirada(1);
                Organismos[super.x][super.y] = Organismos[j][i];
                Organismos[j][i] = new CeldaVacia();
                sobrep = false;
            }else if(aux instanceof Planta){
                if(npasos < 8){
                    this.voltearMirada(3);
                    npasos++;
                }else{
                    this.voltearMirada(super.Azar.nextInt(2));
                    npasos = 0;
                }
                if(sobrep){
                    Organismos[super.x][super.y] = Organismos[j][i];
                    Organismos[j][i] = new Planta(j, i);
                    sobrep = false;
                }else{
                    Organismos[super.x][super.y] = Organismos[j][i];
                    Organismos[j][i] = new CeldaVacia();
                }
                sobrep = true;
            } else if (aux instanceof Oso){
                try {
                    if(((Oso)aux).sexo == 'F'){
                        if(this.sexo == 'F'){
                            ((Animal)aux).mirada = (super.mirada+2)%4;
                            Organismos[x][y] = Organismos[j][i];
                            Organismos[j][i] = aux;
                        } else {
                            reproducirse(Organismos, (Oso) aux, j, i);
                        }
                    }else{
                        if(this.sexo == 'F'){
                            reproducirse(Organismos, (Oso) aux, j, i);
                        } else {
                            if(((Oso)aux).nHijos == this.nHijos){
                                ((Animal)aux).mirada = (super.mirada+2)%4;
                                Organismos[super.x][super.y] = Organismos[j][i];
                                Organismos[j][i] = aux;
                            } else if(((Oso)aux).nHijos > this.nHijos){
                                Organismos[j][i] = new CeldaVacia();
                            } else{
                                Organismos[super.x][super.y] = new CeldaVacia();
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Organismos[super.x][super.y] = Organismos[j][i];
                    Organismos[j][i] = aux;
                }
            } else {
                if(npasos < 8){
                    this.voltearMirada(3);
                    npasos++;
                }else{
                    this.voltearMirada(super.Azar.nextInt(2));
                    npasos = 0;
                }

                if(sobrep){
                    Organismos[super.x][super.y] = Organismos[j][i];
                    Organismos[j][i] = new Planta(j, i);
                    sobrep = false;
                }else{
                    Organismos[super.x][super.y] = Organismos[j][i];
                    Organismos[j][i] = new CeldaVacia();
                }
            }
        }
        
        super.visible = false;
    }

    @Override
    public ImageIcon icon() {
       super.visible = true;
        if(sexo == 'M'){
            switch (super.mirada){
                case 0: return OSOM0;
                case 1: return OSOM1;
                case 2: return OSOM2;
                default: return OSOM3;
            }
        }else{
            switch (super.mirada){
                case 0: return OSOF0;
                case 1: return OSOF1;
                case 2: return OSOF2;
                default: return OSOF3;
            }
        }
    }
}
