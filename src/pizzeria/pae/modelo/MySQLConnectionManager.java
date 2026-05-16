package pizzeria.pae.modelo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;


/**
 *
 * @author adair
 */
public class MySQLConnectionManager {
     private static MySQLConnectionManager instancia;
   
    private Connection connection;
    private String username;
    private String password;
    private String url;
    private String driver;
    
    private MySQLConnectionManager()throws SQLException {
        cargarCredenciales();
        try{
            this.driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
        }catch( ClassNotFoundException ex ){
            ex.printStackTrace();
        }
        
        connect();
        
    }
    
    private void cargarCredenciales(){
        try (InputStream input = MySQLConnectionManager.class.getResourceAsStream("/pae/config/database.properties")){            
            if (input == null) {
                throw new RuntimeException("Unable to find database properties.");
            }            
            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty("db.url");
            username = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load database properties", ex);
        }
    }
    
    private void connect() throws SQLException{
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, password);
        }
    }
    
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    public PreparedStatement prepareStatement(String query) throws SQLException{
        connect();
        return connection.prepareStatement(query);
    }
    
    public static MySQLConnectionManager buildConnection() throws SQLException { 
        if(instancia == null){
            instancia = new MySQLConnectionManager();
        }
        instancia.connect();
        return instancia;
    }
}
