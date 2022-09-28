package controladores;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.*;
import vista.*;

public class ControladorOperacion {
    
    static DB db = new DB();
    static VentanaOperacion ventana = new VentanaOperacion();
    
    public static void mostrar(){// ventana jtable
        ventana.setVisible(true);
        cargarTabla();    
    } 
    ///////////////// LISTADO ////////////////////////////////////// 
    public static void cargarTabla(){//LISTAR para traer en jTable de ventanaProductos, los datos de DB
        DefaultTableModel datos = (DefaultTableModel) ventana.getjTable1().getModel();
        datos.setNumRows(0);
        
        for (Operacion p : db.getOperaciones() ) { //recorre el listado objeto productos de la DB 
    //           for.... System.out.println(p.getNombre());} prueba para mostrar DB en consola
            Object[] fila = new Object[5]; // crea un Array con los datos del objeto
            fila[0] = p.getId();
            fila[1] = p.getOperacion();
            fila[2] = p.getPropiedad();
            fila[3] = p.getDormitorios();
            fila[4] = p.getPrecio();
            datos.addRow(fila);// a la Ventana, le agrega los datos del array fila. 
        }
    }
    //////////////////////////////////////////////////////////    

    public static void botonAgregar() {// toma datos de ventana
        String operacion = ventana.getjTextField4().getText();
        String propiedad = ventana.getjTextField5().getText();
        int dormitorios= Integer.parseInt(ventana.getjTextField6().getText());
        double precio = Double.parseDouble(ventana.getjTextField7().getText());
        db.agregarOperacion(operacion,propiedad,dormitorios,precio);// envia a metodo, en clase DB/agregarProducto, los parametros 
        cargarTabla();// actualiza el listado
    }

//    public static void cargarTablaBusqueda(){//LISTAR para traer en jTable de ventanaProductos, los datos de DB
//          DefaultTableModel datos = (DefaultTableModel) ventana.getjTable1().getModel();
//          datos.setNumRows(0);
//
//        for (Operacion p : db.getOperacionesBusqueda()) { //recorre el listado objeto productos de la DB 
//    //           for.... System.out.println(p.getNombre());} prueba para mostrar DB en consola
//            Object[] fila = new Object[3]; // crea un Array con los datos del objeto
//            fila[0] = p.getId();
//            fila[1] = p.getOperacion();
//            fila[2] = p.getPrecio();
//            datos.addRow(fila);// a la Ventana, le agrega los datos del array fila. 
//        }
//    }    
    
    public static void botonEliminar() {// toma datos de Ventana
        int codigo = Integer.parseInt(ventana.getjTextField3().getText());    
        db.eliminarOperacion(codigo);
        cargarTabla(); 
    
    }
    
    public static void botonModificar() {// toma datos de Ventana
        int codigo = Integer.parseInt(ventana.getjTextField3().getText());
        String operacion = ventana.getjTextField4().getText();
        String propiedad = ventana.getjTextField5().getText();
        int dormitorios= Integer.parseInt(ventana.getjTextField6().getText());
        double precio = Double.parseDouble(ventana.getjTextField7().getText());
        db.modificarOperacion(codigo,operacion,propiedad,dormitorios,precio);
        cargarTabla();
    }
    
    
    
    
    public static void botonListar() {

        cargarTabla();
        
        
    }
    public static void botonBuscar() {
    
    String operacion= ventana.getjTextField1().getText();
    double precio = Double.parseDouble(ventana.getjTextField2().getText());
    String propiedad=ventana.getjComboBox1().getSelectedItem().toString();
    
    ventana.getjTable1().setModel((TableModel) db.getBusqueda(operacion,precio,propiedad));
    System.out.println("buscando");
    }
 
}
