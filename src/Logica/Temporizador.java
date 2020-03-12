
package Logica;

public class Temporizador {
    
    //Atirbutos del temporizador
    private int SEG = 0;
    private int MIN = 0;
    private int HOR = 0;
    //Fin atributos
    
    
    Temporizador(){
        SEG = 0;
        MIN = 0;
        HOR = 0;
    }

    public void click()
    {
        SEG ++;
        if(SEG == 60){
            SEG = 0;
            MIN++;
        }else if(MIN == 60){
            SEG = 0;
            MIN = 0;
            HOR ++;
        }else if(HOR == 24){
            SEG = 0;
            MIN = 0;
            HOR = 0;
        }
    }

    public void setDefault(){
        SEG = 0;
        MIN = 0;
        HOR = 0;
    }

    public int getS()
    {
        return SEG;
    }

    public int getM()
    {
        return MIN;
    }

    public int getH()
    {
        return HOR;
    }
    
    public String tiempo(){
        return "Tiempo simulación: "+HOR+":"+MIN+":"+SEG;
    }
}
