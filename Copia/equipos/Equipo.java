import java.util.ArrayList;

public class Equipo{
    private ArrayList<Persona> personas = new ArrayList<Persona>();

    /**
     * Crea un equipo dado el nombre de sus miembros
     * @param nombres nombres de los miembros del equipo
     */    
    public Equipo(String [] nombres){
        personas= new ArrayList<Persona>();
        for (int i=0; i< nombres.length;i++){
            personas.add(new Persona(nombres[i]));
        }    
    }

    /**
     * Calcula el valor hora de un equipo
     */
    public int valorHora() throws EquipoExcepcion{
        int valor=0;
        for(int i=0; i<personas.size();i++){
            valor+=personas.get(i).valorHora();
        }
        if(valor==0){
            throw new EquipoExcepcion(EquipoExcepcion.EQUIPO_VACIO);
        }
        return valor;

    }

    /**
     * Calcula el valor hora estimado de un equipo.
     * El valor estimado de una persona a la que no se conoce su valor es la
     * media de los valores conocidos
     * Más del 75% del equipo debe tener valores conocidos 
     * @return el valor hora del equipo
     * @throws EquipoException si en el equipo hay una persona desconocida
     * o si no es posible calular el valor estimado
     */
    public int valorHoraEstimado() throws EquipoExcepcion{
        int valor=0;
        int personasConocidas=0;
        int personasDesconocidas=0;
        int valorEstimado=0;
        for(int i=0; i<personas.size();i++){
            try{
                valor+=personas.get(i).valorHora();
                personasConocidas++;
            }
            catch(EquipoExcepcion e){
                if(e.getMessage().equals(EquipoExcepcion.VALOR_DESCONOCIDO)){
                    personasDesconocidas++;
                }
                else if(e.getMessage().equals(EquipoExcepcion.PERSONA_DESCONOCIDA)){
                    throw new EquipoExcepcion(EquipoExcepcion.PERSONA_DESCONOCIDA);
                }
            }
        }
        if(personasConocidas>=(personasConocidas+personasDesconocidas)*0.75){
            valorEstimado=valor+personasDesconocidas*(valor/personasConocidas);
            return valorEstimado;     
        }
        else{
            throw new EquipoExcepcion(EquipoExcepcion.VALOR_ESTIMADO);
        }
        
    }   

}
