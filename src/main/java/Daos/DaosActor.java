
package Daos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.actor;


public class DaosActor extends Daos<actor> {

    //metodos cerrar  
    public void cerrarConexiones(ResultSet rs,PreparedStatement state ){
        try{
            if(rs != null){
              rs.close();
            }
            if(state != null){
               state.close();
            }
        }catch(SQLException e){      
        }
    }
    
    public void seteoNombres(PreparedStatement state,actor actor) throws SQLException{
         state.setString(1, actor.getFirstName());
         state.setString(2, actor.getLastName());
    }
    
    
    @Override
    public actor findById(int id) {
        String query = "SELECT actor_id, first_name, last_name FROM actor where  actor_id = ?";
        actor actor = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        
        try {
            Connection connection = getConnection();
            state = connection.prepareCall(query);
            state.setInt(1, id);
            rs = state.executeQuery();
            if(rs.next()){
                actor = new actor(
                    rs.getInt("actor_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"));          
            }
            connection.commit();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DaosActor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           cerrarConexiones(rs, state);
        }
        return actor;
    }
    //insertar
    public void insert(actor actor) throws SQLException {
        String query = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
        PreparedStatement state = null;
        ResultSet rs = null;
        try{
            Connection connection = getConnection();
            state = connection.prepareCall(query);
            state.setString(1,actor.getFirstName());
            state.setString(2, actor.getLastName());
            state.execute(query);
        }catch(SQLException ex){
             Logger.getLogger(DaosActor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        
            cerrarConexiones(rs, state);
        }
    }

    @Override
    public actor update(actor actor){
    String query = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
    PreparedStatement state = null;

    try {
        Connection connection = getConnection();
        state = connection.prepareStatement(query);
        state.setString(1, actor.getFirstName());
        state.setString(2, actor.getLastName());
        state.setInt(3, actor.getId());

        int filasAfectadas = state.executeUpdate();
        connection.commit();

        if (filasAfectadas > 0) {
            System.out.println("Actor actualizado correctamente.");
            return actor; // Retornamos el actor actualizado
        } else {
            System.out.println("No se encontró el actor con ID: " + actor.getId());
        }
    } catch (SQLException ex) {
        Logger.getLogger(DaosActor.class.getName()).log(Level.SEVERE, "Error al actualizar actor", ex);
    } finally {
        cerrarConexiones(null, state);
    }

    return null; // Si no se actualiza, retornamos null
}


    @Override
    public void delete(int actorId) { // Cambiamos actor por actorId
    String query = "DELETE FROM actor WHERE actor_id = ?";
    PreparedStatement state = null;

    try {
        Connection connection = getConnection();
        state = connection.prepareStatement(query);
        state.setInt(1, actorId); // Solo pasamos el ID
        int filasAfectadas = state.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("Actor eliminado correctamente.");
        } else {
            System.out.println("No se encontró el actor con ID: " + actorId);
        }

        connection.commit();
    } catch (SQLException ex) {
        Logger.getLogger(DaosActor.class.getName()).log(Level.SEVERE, "Error al eliminar actor", ex);
    } finally {
        cerrarConexiones(null, state);
    }
}

  
    public ArrayList<actor> findAll() throws SQLException {
        actor actor = null;
        String query = "SELECT actor_id, first_name, last_name FROM actor";
        ArrayList<actor> arrayActores = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement state = null;
        ResultSet rs = null;
        try{
            state = connection.prepareCall(query); 
          
            rs = state.executeQuery();
            while(rs.next()){
            actor = new actor(
                 rs.getInt("actor_id"),
                 rs.getString("first_name"),
                 rs.getString("last_name"));
                 arrayActores.add(actor);
            }
        }catch(SQLException e){
        
        }finally{
        cerrarConexiones(rs,state );
        }
        
        return arrayActores;
    }



   
    
}
