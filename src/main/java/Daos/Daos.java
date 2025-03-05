
package Daos;

import ConexionBBDD.DbManager;
import static ConexionBBDD.DbManager.getConnection;
import java.sql.Connection;
import java.sql.SQLException;

public  abstract class Daos<T>{
        
    public static Connection getConnection() throws SQLException{  
        return DbManager.getConnection();
    } 
    
    //metodos CRUD
    public abstract T findById(int  id);
    public abstract void insert(T entity) throws SQLException;
    public abstract T update(T entity);
    public abstract void delete(int id);
    
}
