package tp4.pkg2;

public class Main {

    public static void main(String[] args) {
        Parque disney = new Parque();
        Visitante v1 = new Visitante("Juan",123456);
        Visitante v2 = new Visitante("Jorge",456);
        Visitante v3 = new Visitante("Alicia",13456);
        
        disney.registroE(v1);
        disney.registroE(v2);
        disney.registroE(v3);
        
        disney.visitarAtracciones(disney.getColaG().peek());
        disney.visitarAtracciones(disney.getColaG().peek());
        disney.visitarAtracciones(disney.getColaG().peek());
        
        disney.mostrarFila(disney.getCarritos().getCola());
        disney.registroS(v3);
        System.out.println("****************************************************************");
        disney.mostrarFila(disney.getCarritos().getCola());
        
    }
    
}
