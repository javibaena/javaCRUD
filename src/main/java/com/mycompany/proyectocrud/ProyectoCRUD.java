package com.mycompany.proyectocrud;

import ConexionBBDD.DbManager;
import Daos.DaosActor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.actor;

public class ProyectoCRUD {

    private static DaosActor daosActor = new DaosActor();

    public static void main(String[] args) throws SQLException {
        mostrarActorPorId(1); // Llamar a la función para obtener un actor por ID
        mostrarTodosLosActores(); // Llamar a la función para obtener todos los actores
        verificarConexion(); // Llamar a la función para verificar la conexión a la base de datos
    }

    /**
     * Busca y muestra un actor por su ID.
     */
    private static void mostrarActorPorId(int id) {
        actor actor = daosActor.findById(id);
        if (actor != null) {
            System.out.println("Actor encontrado: " + actor.getFirstName() + " " + actor.getLastName());
        } else {
            System.out.println("No se encontró el actor con ID " + id);
        }
    }

    /**
     * Recupera y muestra todos los actores en la base de datos.
     */
    private static void mostrarTodosLosActores() {
        try {
            ArrayList<actor> listaActores = daosActor.findAll();
            if (listaActores.isEmpty()) {
                System.out.println("No hay actores en la base de datos.");
            } else {
                System.out.println("Lista de actores:");
                for (actor a : listaActores) {
                    System.out.println("ID: " + a.getId() + " - Nombre: " + a.getFirstName() + " " + a.getLastName());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los actores: " + e.getMessage());
        }
    }
    
    
    //borrar
  // Método para eliminar un actor
    private static void borrarActor(int actorId) { // Ahora recibe solo el ID
    try {
        daosActor.delete(actorId); // Llamamos a delete con el ID
        System.out.println("Actor eliminado con ID: " + actorId);
    } catch (Exception e) {
        System.out.println("Error al eliminar el actor: " + e.getMessage());
    }
    }
    
 
  // Método para actualizar un actor
        private static void actualizarActor(int id, String nuevoNombre, String nuevoApellido){
             actor actorActualizado = new actor(id, nuevoNombre, nuevoApellido); // Creamos un objeto actualizado
             actor resultado = daosActor.update(actorActualizado);

            if (resultado != null) {
        System.out.println("Actor actualizado: " + resultado.getFirstName() + " " + resultado.getLastName());
        } else {
        System.out.println("No se pudo actualizar el actor.");
    }
            
        
        }
    /**
     * Verifica si la conexión con la base de datos está funcionando correctamente.
     */
    private static void verificarConexion() throws SQLException {
        try (Connection com = DbManager.getConnection()) {
            if (com != null) {
                System.out.println("Conexión realizada con éxito.");
            } else {
                System.out.println("Ocurrió algún error en la conexión.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        } finally {
            DbManager.desconexion();
            System.out.println("Conexión cerrada.");
        }
    }
}
