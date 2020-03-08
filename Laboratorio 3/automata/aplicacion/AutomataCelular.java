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
        new Celula(this,1,1,"indiana");
        new Celula(this,2,2,"007");
        new Izquierdosa(this,3,1,"marx");
        new Izquierdosa(this,3,2, "hegel");
        new Barrera(this,19,0,"suroeste");
        new Barrera(this,0,19,"noreste");
        new Celula(this,3,5,"a");
        new Celula(this,3,6,"b");
        new Celula(this,5,5,"c");
        new Sociable(this,4,6,"Daniel");
        new Sociable(this,3,8,"Paula");
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
