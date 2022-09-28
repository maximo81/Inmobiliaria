
package Principal;
import controladores.*;
import modelo.*;
import vista.VentanaOperacion;

public class Main {

    public static void main(String[] args) {
//        DB db= new DB();
//        db.agregarOperacion("ALQUILER", 45000);

//        Operacion p = new Operacion(); //2da forma, agrega objetos a la DB
//        p.setTipoOperacion("ALQUILER"); p.setPrecio(30000);
//        db.agregarOperacion(p);


//        Operacion p = db.buscar(7);
//        System.out.println(p);
    
        System.out.println("conectado");
        ControladorOperacion.mostrar();  

    }
    
    
}
