package pizzeria.pae.modelo.beans;

/**
 * Bean que representa a un usuario del sistema (cliente o empleado). Los campos
 * fueron inferidos del UsuarioDAO existente.
 */
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String email;
    private boolean haPedido;
    private boolean esEmpleado;
    private boolean esActivo;
    private String nombreUsuario;
    private String contrasenia;
    private Direccion direccion;

    // Constructor vacío — inicializa la dirección para evitar NullPointerException
    public Usuario() {
        this.direccion = new Direccion();
    }

    // Constructor con todos los campos
    public Usuario(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno,
            String telefono, String email, boolean haPedido, boolean esEmpleado,
            boolean esActivo, String nombreUsuario, String contrasenia, Direccion direccion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.email = email;
        this.haPedido = haPedido;
        this.esEmpleado = esEmpleado;
        this.esActivo = esActivo;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.direccion = direccion;
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getHaPedido() {
        return haPedido;
    }

    public void setHaPedido(boolean haPedido) {
        this.haPedido = haPedido;
    }

    public boolean getEsEmpleado() {
        return esEmpleado;
    }

    public void setEsEmpleado(boolean esEmpleado) {
        this.esEmpleado = esEmpleado;
    }

    public boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(boolean esActivo) {
        this.esActivo = esActivo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    // Nombre completo — útil para los ComboBox de la vista
    public String getNombreCompleto() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }

    public String getTipo() {
        return this.esEmpleado ? "Empleado" : "Cliente";
    }

    @Override
    public String toString() {
        return getNombreCompleto();
    }
}
