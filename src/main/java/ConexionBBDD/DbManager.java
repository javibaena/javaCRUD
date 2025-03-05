
package ConexionBBDD;

import io.github.cdimascio.dotenv.Dotenv;
import static io.github.cdimascio.dotenv.DslKt.dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbManager {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");  
    private static final String PASSWORD = dotenv.get("DB_PASS");  
    
    
    public static Connection connection = null;
    //metodo conexion
    public static Connection getConnection() throws SQLException {
        try{
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            throw new SQLException("error en la conexion", e);
        }
            return connection;
    }
    
   // metodo desconexion

   public static void desconexion() throws SQLException{
       try{
           connection.close();
           System.out.println("desconectado con exito");
       }catch(SQLException e){
            throw new SQLException("error en la conexion", e);
       }
   }
}
