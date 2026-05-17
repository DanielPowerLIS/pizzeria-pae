package pizzeria.pae.modelo.beans;

/**
 * Bean que representa la dirección de un usuario.
 */
public class Direccion {

    public int idDireccion;
    public String calle;
    public String ciudad;
    public String numero;
    public String codigoPostal;

    // Constructor vacío
    public Direccion() {
    }

    // Constructor con todos los campos
    public Direccion(int idDireccion, String calle, String ciudad, String numero, String codigoPostal) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.ciudad = ciudad;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
    }

    // Getters y Setters
    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return calle + " #" + numero + ", " + ciudad + " CP " + codigoPostal;
    }
}
