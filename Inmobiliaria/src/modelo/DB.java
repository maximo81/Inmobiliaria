
package modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/////////////////////// CONEXION /////////////////////////////////////////////
public class DB {
    private String url = "jdbc:mariadb://localhost/inmobiliaria";
    private String usr="root";
    private String clave= "1234";
    
    public Connection getConnection () throws SQLException{
        return DriverManager.getConnection(url,usr,clave);
    }
    
    public void ejecutar(String sql){ // obtiene cadena, conexion y la envia
        try {
            Connection c = getConnection(); //llamada de funcion y obtiene la conexion
            Statement s= c.createStatement();// devuelve objeto Statement
            s.executeUpdate(sql);// --> envia orden a mariaDB (INSERT, DELETE, UPDATE)
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(sql);
    }
    
    ////////////////////////////// ALTA /////////////////////////////////////////    
    public void agregarOperacion(String operacion, String propiedad, int dormitorios ,double precio){//parametros con datos de VentanaOperacion
        
        String sql= "INSERT INTO operacion (operacion, propiedad,dormitorios,precio) values('%1','%2','%3','%4')";
        sql= sql.replace("%1", operacion);
        sql= sql.replace("%2", propiedad);
        sql= sql.replace("%3", Integer.toString(dormitorios));
        sql= sql.replace("%4", Double.toString(precio));
        ejecutar(sql);
    }
    
    public void agregarOperacion(Operacion p){ // SObrecarga de POO: recibe un objeto y llama al otro metodo agre...que recibe nombre y precio
        agregarOperacion(p.getOperacion(),p.getPropiedad(),p.getDormitorios(),p.getPrecio());
        
    }
    
    /////////////////////////////// BAJA //////////////////////////////////////////    
    public void eliminarOperacion(int id){

        String sql= "DELETE FROM operacion WHERE id = %1";
        sql= sql.replace("%1", Integer.toString(id));
        ejecutar(sql);
    }
/////////////////////////////// MODIFICACION //////////////////////////////////    
    public void modificarOperacion(int id, String operacion,String propiedad, int dormitorios ,double precio){

        String sql = "UPDATE operacion set operacion = '%1', propiedad='%2', dormitorios='%3', precio='%4' where id=" + id ;
        sql= sql.replace("%1", operacion);
        sql= sql.replace("%2", propiedad);
        sql= sql.replace("%3", Integer.toString(dormitorios));
        sql= sql.replace("%4", Double.toString(precio));
        ejecutar(sql);
    }
    public void modificarOperacion(Operacion p){ // SObrecarga de POO
        modificarOperacion(p.getId(), p.getOperacion(),p.getPropiedad(),p.getDormitorios(),p.getPrecio());    
    }
   /////////////////////////////// LISTADO  ////////////////////////////////////// casi igual que buscar    
    public ArrayList<Operacion> getOperaciones(){ // funcion que devuelve un ArrayList

        ArrayList<Operacion> operaciones = new ArrayList<>();// crea un objeto ArrayList VACIO
        try{
            Connection c = getConnection();
            ResultSet res = 
                    c.createStatement()
                            .executeQuery("SELECT * from operacion");// obtener Todos los datos de mariaDB
            while (res.next()){// mientras haya filas en mariaDB
                Operacion p = new Operacion();// crea objeto Producto
                p.setId(res.getInt("id"));// cargo el objeto con datos de mariaDB,"id" nombres de campos en mariaDB
                p.setOperacion(res.getString("operacion"));
                p.setPropiedad(res.getString("propiedad"));
                p.setDormitorios(res.getInt("dormitorios"));
                p.setPrecio(res.getDouble("precio"));
                operaciones.add(p);// al arraylist "productos", agrego "coleccion de objetos" con datos de DB  
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return operaciones;// devuelve el ArrayList COMPLETO, con objetos con los datos de mariaDB
    }
    
    //////////////////////////////// BUSCAR //////////// LISTADO FILTRADO ////////////////////////    
    public DefaultTableModel getBusqueda(String operacion,double precio, String propiedad){

        DefaultTableModel datos= new DefaultTableModel();
        datos.addColumn("id");
        datos.addColumn("operacion");
        datos.addColumn("propiedad");
        datos.addColumn("dormitorios");
        datos.addColumn("precio");
        
        try {
            Connection c = getConnection();
            
            PreparedStatement sql=c.prepareStatement("SELECT * FROM operacion WHERE operacion=? and precio=? and propiedad=?");
            sql.setString(1,operacion);
            sql.setDouble(2,precio);
            sql.setString(3,propiedad); 
            
            ResultSet res= sql.executeQuery();
            
            while(res.next()){
                
                Object[] fila= new Object[5];
                         fila[0]= res.getString("id");
                         fila[1]= res.getString("operacion");
                         fila[2]= res.getString("propiedad");
                         fila[3]= res.getString("dormitorios");
                         fila[4]= res.getString("precio");
                         datos.addRow(fila);
            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return datos;
    }
    
    
    
    
}    