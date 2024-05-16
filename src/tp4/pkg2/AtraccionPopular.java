package tp4.pkg2;

import java.util.LinkedList;
import java.util.Queue;

public class AtraccionPopular {
    private Queue<Visitante> cola= new LinkedList<>();
    private String nombre;

    public AtraccionPopular(String nombre) {
        this.nombre = nombre;
    }

    public Queue<Visitante> getCola() {
        return cola;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    
    
}
