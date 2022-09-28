
package modelo;

public class Operacion {
    private int id;
    private String operacion;
    private String propiedad;
    private int dormitorios; 
    private double precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(String propiedad) {
        this.propiedad = propiedad;
    }

    public int getDormitorios() {
        return dormitorios;
    }

    public void setDormitorios(int dormitorios) {
        this.dormitorios = dormitorios;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Operacion(int id, String operacion, String propiedad, int dormitorios, double precio) {
        this.id = id;
        this.operacion = operacion;
        this.propiedad = propiedad;
        this.dormitorios = dormitorios;
        this.precio = precio;
    }

    public Operacion() {
    }

    @Override
    public String toString() {
        return "Operacion{" + "id=" + id + ", operacion=" + operacion + ", propiedad=" + propiedad + ", dormitorios=" + dormitorios + ", precio=" + precio + '}';
    }

}    