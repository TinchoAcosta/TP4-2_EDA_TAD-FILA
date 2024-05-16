package tp4.pkg2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Parque {
    private Queue<Visitante> colaG= new LinkedList<>();
    private Queue<Visitante> colaRegistros= new LinkedList<>();
    private AtraccionPopular montaña;
    private AtraccionPopular carritos;
    private AtraccionPopular vuelta;
    private int max=5;
    private int gente=0;

    public Parque() {
        montaña=new AtraccionPopular("Montaña rusa");
        carritos=new AtraccionPopular("Carritos chocadores");
        vuelta=new AtraccionPopular("Vuelta al Mundo");
    }

    public Queue<Visitante> getColaG() {
        return colaG;
    }

    public AtraccionPopular getMontaña() {
        return montaña;
    }

    public AtraccionPopular getCarritos() {
        return carritos;
    }

    public AtraccionPopular getVuelta() {
        return vuelta;
    }
    
    
    
    public void registroE(Visitante x){
        if(!parqueLleno()){
            colaG.add(x); 
            colaRegistros.add(x);
            gente++;
        }else{
            System.out.println("Parque lleno.");
        }
    }
    
    public void registroS(Visitante x){
        Queue<Visitante> aux= new LinkedList<>();
        if(!colaRegistros.isEmpty()){
        int pos = retirarVisitante(x);
            for (int i = 1; i < pos; i++){
                switch(x.getTicket()){
                    case 0: {                      
                        aux.add(colaG.poll());                                                   
                        break;
                    }
                    case 1: {                        
                        aux.add(montaña.getCola().poll());                                                   
                        break;
                    }
                    case 2: {                       
                        aux.add(carritos.getCola().poll());                                                   
                        break;
                    }
                    case 3: {                        
                        aux.add(vuelta.getCola().poll());                                                    
                        break;
                    }
                }
                
            }    
                
            switch(x.getTicket()){
                case 0:{
                    colaG.poll();
                    while(!colaG.isEmpty()){
                        aux.add(colaG.poll());
                    }
                    while(!aux.isEmpty()){
                        colaG.add(aux.poll());
                    }
                }
                case 1:{
                    montaña.getCola().poll();
                    while(!montaña.getCola().isEmpty()){
                        aux.add(montaña.getCola().poll());
                    }
                    while(!aux.isEmpty()){
                        montaña.getCola().add(aux.poll());
                    }
                }
                case 2:{
                    carritos.getCola().poll();
                    while(!carritos.getCola().isEmpty()){
                        aux.add(carritos.getCola().poll());
                    }
                    while(!aux.isEmpty()){
                        carritos.getCola().add(aux.poll());
                    }
                }
                case 3:{
                    vuelta.getCola().poll();
                    while(!vuelta.getCola().isEmpty()){
                        aux.add(vuelta.getCola().poll());
                    }
                    while(!aux.isEmpty()){
                        vuelta.getCola().add(aux.poll());
                    }
                }
            }
            
            gente--;
        }else{
            System.out.println("Parque vacio.");
        }
    }
    
    public void visitarAtracciones(Visitante x){ //VISITANTE RECIBIDO CON PEEK
        String opcion;
        Scanner leer = new Scanner(System.in);
        
        if (x == null){
            System.out.println("FILA VACIA, no hay visitantes.");
        }else{        
            System.out.println("Indique la atraccion que desee:");
            System.out.println("M: montaña rusa \n C: carritos chocadores \n V: vuelta al mundo");
            opcion = leer.nextLine().toUpperCase();

            switch(opcion){
                case "M": {
                    switch (x.getTicket()) {
                        case 0 -> venderTicket(colaG.poll(),"Montaña rusa");
                        case 2 -> venderTicket(carritos.getCola().poll(),"Montaña rusa");
                        case 3 -> venderTicket(vuelta.getCola().poll(),"Montaña rusa");
                        case 1 -> System.out.println(x.getNombre()+" ya se encuantra en la montaña rusa.");
                    }
                    break;
                }
                case "C": {
                    switch (x.getTicket()) {
                        case 0 -> venderTicket(colaG.poll(),"Carritos");
                        case 1 -> venderTicket(montaña.getCola().poll(),"Carritos");
                        case 3 -> venderTicket(vuelta.getCola().poll(),"Carritos");
                        case 2 -> JOptionPane.showMessageDialog(null, x.getNombre()+" ya se encuantra en los carritos chocadores.");
                    }
                    break;
                }
                case "V": {
                    switch (x.getTicket()) {
                        case 0 -> venderTicket(colaG.poll(),"Vuelta");
                        case 2 -> venderTicket(carritos.getCola().poll(),"Vuelta");
                        case 1 -> venderTicket(montaña.getCola().poll(),"Vuelta");
                        case 3 -> JOptionPane.showMessageDialog(null, x.getNombre()+" ya se encuantra en la vuelta al mundo.");
                    }
                    break;
                }
                default: {
                    JOptionPane.showMessageDialog(null, x.getNombre()+" no pudo hacer nada.");
                }
            }           
        }
        
    }
    
    private void venderTicket(Visitante x,String s){
        switch(s){
            case "Montaña rusa": {
                x.setTicket(1);
                montaña.getCola().add(x); 
                System.out.println(x.getNombre()+" ha comprado un ticket para la Montaña Rusa.");
                break;
            }
            case "Carritos": {
                x.setTicket(2);
                carritos.getCola().add(x);
                System.out.println(x.getNombre()+" ha comprado un ticket para los Carritos Chocadores.");
                break;
            }
            case "Vuelta": {
                x.setTicket(3);
                vuelta.getCola().add(x);
                System.out.println(x.getNombre()+" ha comprado un ticket para la Vuelta Al Mundo.");
                break;
            }
        }
    }

    private boolean parqueLleno() {
        return gente == max;
    }
    
    public void buscarVisitantePorNombre(String nombre){
        Queue<Visitante> aux= new LinkedList<>();
        boolean encuentro=false;
        while(!colaRegistros.isEmpty()){
            aux.add(colaRegistros.peek());
            if(colaRegistros.peek().getNombre().equalsIgnoreCase(nombre)){
                switch(colaRegistros.peek().getTicket()){
                    case 0 -> System.out.println(nombre + " ha sido encontrado/a en la fila general!");
                    case 1 -> System.out.println(nombre + " ha sido encontrado/a en la montaña rusa!");
                    case 2 -> System.out.println(nombre + " ha sido encontrado/a en los carritos chocadores!");
                    case 3 -> System.out.println(nombre + " ha sido encontrado/a en la vuelta al mundo!");
                }                
                encuentro = true;                
            }
            colaRegistros.poll();
        }
        while(!aux.isEmpty()){
            colaRegistros.add(aux.poll());
        }
        if(!encuentro){
            System.out.println(nombre+" no se encuentra en el parque.");
        }
    }
    
    public void buscarVisitantePorDni(int dni){
        Queue<Visitante> aux= new LinkedList<>();
        boolean encuentro=false;
        while(!colaRegistros.isEmpty()){
            aux.add(colaRegistros.peek());
            if(colaRegistros.peek().getDni() == dni){
                switch(colaRegistros.peek().getTicket()){
                    case 0 -> System.out.println(colaRegistros.peek().getNombre() + " ha sido encontrado/a en la fila general!");
                    case 1 -> System.out.println(colaRegistros.peek().getNombre() + " ha sido encontrado/a en la montaña rusa!");
                    case 2 -> System.out.println(colaRegistros.peek().getNombre() + " ha sido encontrado/a en los carritos chocadores!");
                    case 3 -> System.out.println(colaRegistros.peek().getNombre() + " ha sido encontrado/a en la vuelta al mundo!");
                }                
                encuentro = true;                
            }
            colaRegistros.poll();
        }
        while(!aux.isEmpty()){
            colaRegistros.add(aux.poll());
        }
        if(!encuentro){
            System.out.println("No se encuentra en el parque ninguna persona con dni: "+dni);
        }
    }
    
    private int retirarVisitante(Visitante x){
        Queue<Visitante> aux= new LinkedList<>();
        boolean encuentro=false;
        int posicion = 1;
        
        switch(x.getTicket()){
            case 0: {
                while(!colaG.isEmpty()){
                    aux.add(colaG.peek());
                    if(colaG.peek().getDni() == x.getDni()){              
                        encuentro = true;                
                    }
                    colaG.poll();
                    if(!encuentro){
                        posicion++;
                    }
                }
                while(!aux.isEmpty()){
                    colaG.add(aux.poll());
                }
                break;
            }
            case 1: {
                while(!montaña.getCola().isEmpty()){
                    aux.add(montaña.getCola().peek());
                    if(montaña.getCola().peek().getDni() == x.getDni()){              
                        encuentro = true;                
                    }
                    montaña.getCola().poll();
                    if(!encuentro){
                        posicion++;
                    }
                }
                while(!aux.isEmpty()){
                    montaña.getCola().add(aux.poll());
                }
                break;
            }
            case 2: {
                while(!carritos.getCola().isEmpty()){
                    aux.add(carritos.getCola().peek());
                    if(carritos.getCola().peek().getDni() == x.getDni()){              
                        encuentro = true;                
                    }
                    carritos.getCola().poll();
                    if(!encuentro){
                        posicion++;
                    }
                }
                while(!aux.isEmpty()){
                    carritos.getCola().add(aux.poll());
                }
                break;
            }
            case 3: {
                while(!vuelta.getCola().isEmpty()){
                    aux.add(vuelta.getCola().peek());
                    if(vuelta.getCola().peek().getDni() == x.getDni()){              
                        encuentro = true;                
                    }
                    vuelta.getCola().poll();
                    if(!encuentro){
                        posicion++;
                    }
                }
                while(!aux.isEmpty()){
                    vuelta.getCola().add(aux.poll());
                }
                break;
            }
        }
        if(!encuentro){
            System.out.println(x.getNombre()+" no se encuentra en el parque.");
            return 0;
        }
        return posicion;
    }
    
    //PRUEBA//
    public void mostrarFila(Queue<Visitante> fila){
        Queue<Visitante> aux = new LinkedList<>();
        if(fila.isEmpty()){
            System.out.println("Fila vacia.");
        }else{
            while(!fila.isEmpty()){
                System.out.println("------ VISITANTE ------");
                System.out.println("Nombre: "+fila.peek().getNombre());
                System.out.println("DNI: "+fila.peek().getDni());
                System.out.println("TICKET: "+fila.peek().getTicket());
                aux.add(fila.poll());
            }
            while(!aux.isEmpty()){
                fila.add(aux.poll());
            }            
        }
    }
    //PRUEBA//
}
