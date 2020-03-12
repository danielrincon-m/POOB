package aplicacion;

import java.util.*;

public class AutomataCelular{
    static private int LONGITUD=20;
    private Elemento[][] automata;
    

        
    public AutomataCelular() {
        automata=new Elemento[LONGITUD][LONGITUD];
        for (int f=0;f<LONGITUD;f++){
            for (int c=0;c<LONGITUD;c++){
                automata[f][c]=null;
            }
        }
        
        algunosElementos();
    }

    public int  getLongitud(){
        return LONGITUD;
    }

    public Elemento getElemento(int f,int c){
        return automata[f][c];
    }

    public void setElemento(int f, int c, Elemento nueva){
        automata[f][c]=nueva;
    }

    public void algunosElementos(){
        //Celulas
        new Celula(this,1,1,"indiana");
        new Celula(this,2,2,"007");
        //Izquierdosas
        new Izquierdosa(this,3,1,"marx");
        new Izquierdosa(this,3,2, "hegel");
        //Barreras
        new Barrera(this,19,0,"suroeste");
        new Barrera(this,0,19,"noreste");
        //sociables
        new Celula(this,3,5,"a");
        new Celula(this,3,6,"b");
        new Celula(this,5,5,"c");
        new Sociable(this,4,6,"Daniel");
        new Sociable(this,3,8,"Paula");
        //Infecciosas
        new Infeccioso(this,8,2,"i1");
        new Infeccioso(this,8,5,"i2");
        new Celula(this,7,3,"ca");
        new Celula(this,9,1,"cb");
        new Celula(this,7,1,"cc");
        new Barrera(this,9,3,"cd");
    }
    public void ticTac(){
        for(int i=0; i< LONGITUD; i++){
            for(int j=0; j< LONGITUD; j++){
                if(automata[i][j]!=null){
                    automata[i][j].decida();
                    automata[i][j].cambie();
                }
            }
        }
    }

}
